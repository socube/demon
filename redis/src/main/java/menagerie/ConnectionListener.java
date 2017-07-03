package menagerie;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public interface ConnectionListener {


    public void syncConnected();


    public void expired();


    public void disconnected();
}
