package menagerie;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
@Beta
public interface Serializer<T> {


    public byte[] serialize(T instance);

    public T deserialize(byte[] data);
}
