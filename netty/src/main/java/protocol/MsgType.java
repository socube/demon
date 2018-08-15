package protocol;

/**
 * @Description 消息类型
 * @Author xuedong.wang
 * @Date 17/5/8.
 */
public enum MsgType {

    EMGW_LOGIN_REQ((byte) 0x00),
    EMGW_LOGIN_RES((byte) 0x01);

    private byte value;

    public byte getValue() {
        return value;
    }

    private MsgType(byte value) {
        this.value = value;
    }
}

