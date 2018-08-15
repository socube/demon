package menagerie.collections;

import menagerie.Serializer;

import java.io.*;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public final class JavaEntrySerializer<K extends Serializable, V extends Serializable> implements Serializer<Map.Entry<K,V>> {


    @Override
    @SuppressWarnings({"unchecked"})
    public Map.Entry<K, V> deserialize(byte[] data) {
        try {
            ObjectInputStream inputStream  = new ObjectInputStream(new ByteArrayInputStream(data));
            return (AbstractMap.SimpleEntry<K,V>)inputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            //should never happen, since AbstractMap.SimpleEntry is part of the JDK
            throw new RuntimeException(e);
        }
    }


    @Override
    public byte[] serialize(Map.Entry<K, V> instance) {
        try {
            ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
            ObjectOutputStream arrayOutput = new ObjectOutputStream(byteArrayStream);
            arrayOutput.writeObject(instance);
            arrayOutput.flush();

            byte[] bytes = byteArrayStream.toByteArray();
            arrayOutput.close();
            return bytes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
