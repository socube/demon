package timer;

import java.net.SocketAddress;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/1/9.
 */
public class TimeClientException  extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TimeClientException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TimeClientException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public TimeClientException(SocketAddress address) {
        super(address.toString());
    }
}
