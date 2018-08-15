package menagerie.collections;

import menagerie.ClusterSafe;
import menagerie.Serializer;
import menagerie.ZkSessionManager;
import menagerie.ZkUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
@ClusterSafe
public final class ZkHashMap<K,V>  implements ConcurrentMap<K,V> {


    private final ZkSegment<K,V>[] segments;

    private final int segmentShift;

    private final int segmentMask;



    public ZkHashMap(String mapNode, ZkSessionManager zkSessionManager, Serializer<Entry<K,V>> serializer){
        this(mapNode,zkSessionManager, ZooDefs.Ids.OPEN_ACL_UNSAFE,serializer);
    }

    public ZkHashMap(String mapNode, ZkSessionManager zkSessionManager,Serializer<Entry<K,V>> serializer, int concurrency){
        this(mapNode,zkSessionManager, ZooDefs.Ids.OPEN_ACL_UNSAFE,serializer,concurrency);
    }


    public ZkHashMap(String mapNode, ZkSessionManager zkSessionManager,
                     List<ACL> privileges, Serializer<Entry<K,V>> serializer) {
        this(mapNode, zkSessionManager,privileges,serializer,16);
    }


    public ZkHashMap(String mapNode, ZkSessionManager zkSessionManager,
                     List<ACL> privileges, Serializer<Entry<K,V>> serializer, int concurrency) {
        /*
        Shamelessly stolen from the implementation of ConcurrentHashMap
        */
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrency) {
            ++sshift;
            ssize <<= 1;
        }
        segmentShift = 32 - sshift;
        segmentMask = ssize - 1;
        this.segments = ZkSegment.newArray(ssize);

        //need to build the map in a single, synchronized activity across all members
        Lock mapLock = new ReentrantZkLock(mapNode,zkSessionManager,privileges);
        mapLock.lock();
        try{
            //need to read the data out of zookeeper
            ZooKeeper zk = zkSessionManager.getZooKeeper();
            for(int i=0;i< segments.length;i++){
                try {
                    //attach the first segments to any segments which are already created
                    List<String> zkBuckets = ZkUtils.filterByPrefix(zk.getChildren(mapNode,false),"bucket");
                    ZkUtils.sortBySequence(zkBuckets,'-');
                    int numBucketsBuilt = 0;
                    for(int bucketIndex=0;bucketIndex<zkBuckets.size()&&bucketIndex< segments.length;bucketIndex++){
                        numBucketsBuilt++;
                        segments[bucketIndex] = new ZkSegment<K,V>(mapNode+"/"+zkBuckets.get(bucketIndex),
                                serializer,zkSessionManager,privileges);
                    }

                    //create any additional segments as needed
                    while(numBucketsBuilt< segments.length){
                        String bucketNode = ZkUtils.safeCreate(zk, mapNode + "/bucket-",
                                new byte[]{}, privileges, CreateMode.PERSISTENT_SEQUENTIAL);
                        segments[numBucketsBuilt] = new ZkSegment<K,V>(bucketNode,
                                serializer,zkSessionManager,privileges);
                        numBucketsBuilt++;
                    }
                } catch (KeeperException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }finally{
            mapLock.unlock();
        }
    }
    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }




    private static final class ZkSegment<K,V>{
        private static final String entryPrefix = "entry";
        private static final char entryDelimiter = '-';
        private final String bucketNode;
        private final ZkSessionManager sessionManager;
        private final List<ACL> nodePrivileges;
        private final ReadWriteLock safety;
        private final Serializer<Entry<K,V>> serializer;

        private ZkSegment(String bucketNode, Serializer<Entry<K,V>> serializer,
                          ZkSessionManager sessionManager, List<ACL> nodePrivileges) {
            this.bucketNode = bucketNode;
            this.sessionManager = sessionManager;
            this.nodePrivileges = nodePrivileges;
            this.serializer = serializer;

            safety = new ReentrantZkReadWriteLock(bucketNode,sessionManager,nodePrivileges);
        }

        V get(Object key, int hash) {
            Lock readLock = safety.readLock();
            readLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), entryPrefix + "-" + hash);
                for(String match: hashMatches){
                    byte[] data = ZkUtils.safeGetData(zk, bucketNode + "/" + match, false, new Stat());
                    if(data.length>0){
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)){
                            return deserializedEntry.getValue();
                        }
                    }
                }
                return null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                readLock.unlock();
            }
        }

        boolean containsKey(Object key, int hash) {
            Lock readLock = safety.readLock();
            readLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), getHashedPrefix(hash));

                for(String match: hashMatches){
                    byte[] data = ZkUtils.safeGetData(zk, bucketNode + "/" + match, false, new Stat());
                    if(data.length>0){
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)){
                            return true;
                        }
                    }
                }
                return false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                readLock.unlock();
            }
        }

        boolean containsValue(Object value) {
            Lock readLock = safety.readLock();
            readLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), entryPrefix);
                for(String match: hashMatches){
                    Stat exists = zk.exists(bucketNode+"/"+match,false);
                    if(exists!=null){
                        byte[] data = zk.getData(bucketNode+"/"+match,false,exists);
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getValue().equals(value)){
                            return true;
                        }
                    }
                }
                return false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                readLock.unlock();
            }
        }

        boolean replace(K key,int hash, V oldValue, V newValue){
            Lock writeLock = safety.writeLock();
            writeLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false),
                        getHashedPrefix(hash));
                for(String match: hashMatches){
                    Stat exists = zk.exists(bucketNode+"/"+match,false);
                    if(exists!=null){
                        byte[] data = zk.getData(bucketNode+"/"+match,false,exists);
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)&&deserializedEntry.getValue().equals(oldValue)){
                            //we can set the value
                            zk.setData(bucketNode+"/"+match,serializer.serialize(new AbstractMap.SimpleEntry<K,V>(key,newValue)),-1);
                            return true;
                        }
                    }
                }
                return false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                writeLock.unlock();
            }
        }

        V replace(K key, int hash, V newValue){
            Lock writeLock = safety.writeLock();
            writeLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), getHashedPrefix(hash));
                for(String match: hashMatches){
                    byte[] data = ZkUtils.safeGetData(zk,bucketNode+"/"+match,false,new Stat());
                    if(data.length>0){
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)){
                            //we can set the value
                            zk.setData(bucketNode+"/"+match,serializer.serialize(new AbstractMap.SimpleEntry<K,V>(key,newValue)),-1);
                            return newValue;
                        }
                    }
                }
                return null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                writeLock.unlock();
            }
        }

        V put(K key, int hash, V value,boolean onlyIfAbsent){
            Lock writeLock = safety.writeLock();
            writeLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), entryPrefix + "-" + hash);
                for(String match: hashMatches){

                    byte[] data = ZkUtils.safeGetData(zk,bucketNode+"/"+match,false, new Stat());
                    if(data.length>0){
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)){
                            if(onlyIfAbsent){
                                //it isn't absent, so we can't put it
                                return deserializedEntry.getValue();
                            }
                            //we found our match, serialize and set the data
                            zk.setData(bucketNode+"/"+match,serializer.serialize(new AbstractMap.SimpleEntry<K,V>(key,value)),-1);
                            return deserializedEntry.getValue();
                        }
                    }
                }

                //if we make it this far, we didn't find any matches, so just create another entry in this bucket
                zk.create(bucketNode+"/"+ entryPrefix + entryDelimiter +hash+ entryDelimiter,serializer.serialize(new AbstractMap.SimpleEntry<K,V>(key,value)),nodePrivileges, CreateMode.PERSISTENT_SEQUENTIAL);
                return value;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                writeLock.unlock();
            }
        }

        V remove(Object key, int hash, Object oldValue){
            Lock writeLock = safety.writeLock();
            writeLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> hashMatches = ZkUtils.filterByPrefix(zk.getChildren(bucketNode, false), getHashedPrefix(hash));
                for(String match: hashMatches){
                    Stat exists = zk.exists(bucketNode+"/"+match,false);
                    if(exists!=null){
                        byte[] data = zk.getData(bucketNode+"/"+match,false,exists);
                        Map.Entry<K,V> deserializedEntry = serializer.deserialize(data);
                        if(deserializedEntry.getKey().equals(key)){
                            if(oldValue==null||deserializedEntry.getValue().equals(oldValue)){
                                ZkUtils.safeDelete(zk,bucketNode+"/"+match,-1);
                                return deserializedEntry.getValue();
                            }
                        }
                    }
                }
                return null;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                writeLock.unlock();
            }
        }

        void clear(){
            Lock writeLock = safety.writeLock();
            writeLock.lock();
            try{
                ZooKeeper zk = sessionManager.getZooKeeper();
                List<String> elements = ZkUtils.filterByPrefix(zk.getChildren(bucketNode,false),entryPrefix);
                for(String entry:elements){
                    ZkUtils.safeDelete(zk,bucketNode+"/"+entry,-1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } finally{
                writeLock.unlock();
            }
        }

        int size(){
            try {
                return ZkUtils.filterByPrefix(sessionManager.getZooKeeper().getChildren(bucketNode,false), entryPrefix).size();
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        boolean isEmpty() {
            return size()<=0;
        }

        void acquireReadLock(){
            safety.readLock().lock();
        }

        void releaseReadLock(){
            safety.readLock().unlock();
        }

        private String getHashedPrefix(int hash) {
            return entryPrefix + entryDelimiter + hash;
        }

        public Iterator<Entry<K, V>> entryIterator() {
            return new ZkReadWriteIterator<Entry<K,V>>(bucketNode,serializer,sessionManager,nodePrivileges,entryPrefix,entryDelimiter,safety);
        }

        @SuppressWarnings({"unchecked"})
        private static <K,V> ZkSegment<K,V>[] newArray(int size) {
            return new ZkSegment[size];
        }
    }
}
