package protocol;

/**
 * @Description 消息对象
 * @Author xuedong.wang
 * @Date 17/5/8.
 */
public class ProtocolMsg {

    private ProtocolHeader protocolHeader = new ProtocolHeader();
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    /**
     *
     */
    public ProtocolMsg() {
        // TODO Auto-generated constructor stub
    }

    public ProtocolHeader getProtocolHeader() {
        return protocolHeader;
    }

    public void setProtocolHeader(ProtocolHeader protocolHeader) {
        this.protocolHeader = protocolHeader;
    }
}
