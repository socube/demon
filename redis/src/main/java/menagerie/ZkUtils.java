package menagerie;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class ZkUtils {

    /*Can't instantiate me!*/
    private ZkUtils(){}


    public static void sortBySequence(List<String> items, char sequenceDelimiter){
        Collections.sort(items,new SequenceComparator(sequenceDelimiter));
    }


    public static void sortByReverseSequence(List<String> items,char sequenceDelimiter){
        Collections.sort(items,Collections.reverseOrder(new SequenceComparator(sequenceDelimiter)));
    }


    public static int  parseSequenceNumber(String node,char sequenceStartDelimiter){
        String sequenceStr = parseSequenceString(node,sequenceStartDelimiter);
        return Integer.parseInt(sequenceStr);
    }


    public static String parseSequenceString(String node, char sequenceStartDelimiter){
        if(node==null)throw new NullPointerException("No node specified!");

        int seqStartIndex = node.lastIndexOf(sequenceStartDelimiter);
        if(seqStartIndex<0)
            throw new IllegalArgumentException("No sequence is parseable from the specified node: " +
                    "Node= <"+node+">, sequence delimiter=<"+sequenceStartDelimiter+">");

        return node.substring(seqStartIndex+1);
    }

    public static List<String> filterByPrefix(List<String> nodes,String... prefixes) {
        List<String> lockChildren = new ArrayList<String>();
        for(String child:nodes){
            for(String prefix:prefixes){
                if(child.startsWith(prefix)){
                    lockChildren.add(child);
                    break;
                }
            }
        }
        return lockChildren;
    }

    public static boolean safeDelete(ZooKeeper zk, String nodeToDelete, int version)throws KeeperException, InterruptedException{
        try {
            zk.delete(nodeToDelete,version);
            return true;
        } catch (KeeperException ke) {
            //if the node has already been deleted, don't worry about it
            if(ke.code()!=KeeperException.Code.NONODE)
                throw ke;
            else
                return false;
        }
    }


    public static void safeDeleteAll(ZooKeeper zk, int version, String... nodesToDelete) throws KeeperException, InterruptedException {
        for(String permitNode:nodesToDelete){
            ZkUtils.safeDelete(zk,permitNode,version);
        }
    }


    public static void recursiveSafeDelete(ZooKeeper zk, String nodeToDelete, int version) throws KeeperException, InterruptedException{
        try{
            List<String> children = zk.getChildren(nodeToDelete,false);
            for(String child: children){
                recursiveSafeDelete(zk,nodeToDelete+"/"+child,version);
            }
            //delete this node
            safeDelete(zk,nodeToDelete,version);
        }catch(KeeperException ke){
            if(ke.code()!=KeeperException.Code.NONODE)
                throw ke;
        }
    }


    public static String safeCreate(ZooKeeper zk, String nodeToCreate,byte[] data, List<ACL> privileges, CreateMode createMode) throws KeeperException,InterruptedException {
        try {
            return zk.create(nodeToCreate,data,privileges,createMode);
        } catch (KeeperException ke) {
            //if the node has already been created, don't worry about it
            if(ke.code()!=KeeperException.Code.NODEEXISTS)
                throw ke;
            else{
                /*
                This only happens if {@code createMode} is EPHEMERAL or PERSISTENT--sequential modes do not throw
                this error code.
                In the case that we are looking for EPHEMERAL or PERSISTENT, then we already know the full path
                of the node we were trying to create, so just return that, since it already exists.
                */
                return nodeToCreate;
            }

        }
    }

    public static byte[] safeGetData(ZooKeeper zk, String node, Watcher watcher, Stat stat) throws InterruptedException, KeeperException {
        try {
            return zk.getData(node,watcher,stat);
        } catch (KeeperException e) {
            if(e.code()!=KeeperException.Code.NONODE)
                throw e;
            else
                return new byte[]{};
        }
    }


    public static byte[] safeGetData(ZooKeeper zk, String node, boolean watch, Stat stat) throws InterruptedException, KeeperException {
        try {
            return zk.getData(node,watch,stat);
        } catch (KeeperException e) {
            if(e.code()!=KeeperException.Code.NONODE)
                throw e;
            else
                return new byte[]{};
        }
    }

    public static String recursiveSafeCreate(ZooKeeper zk, String node,byte[] data,List<ACL> privileges,CreateMode createMode) throws KeeperException,InterruptedException{
        if(node==null||node.length()<0)return node; //nothing to do
        else if("/".equals(node))return node; //can't create any further
        else{
            int index = node.lastIndexOf("/");
            if(index==-1) return node; //nothing to do
            String parent = node.substring(0,index);
            //make sure that the parent has been created
            recursiveSafeCreate(zk,parent,data,privileges,createMode);

            //create this node now
            return safeCreate(zk,node,data,privileges,createMode);
        }
    }

    /*--------------------------------------------------------------------------------------------------------------------*/
    /*private helper classes*/
    private static class SequenceComparator implements Comparator<String> {
        private final char sequenceDelimiter;

        public SequenceComparator(char sequenceDelimiter) {
            this.sequenceDelimiter = sequenceDelimiter;
        }

        @Override
        public int compare(String child1, String child2) {
            long childOneSeqNbr = ZkUtils.parseSequenceNumber(child1,sequenceDelimiter);
            long childTwoSeqNbr = ZkUtils.parseSequenceNumber(child2,sequenceDelimiter);

            if(childOneSeqNbr<childTwoSeqNbr)return -1;
            else if(childOneSeqNbr>childTwoSeqNbr)return 1;
            else return 0;
        }
    }
}
