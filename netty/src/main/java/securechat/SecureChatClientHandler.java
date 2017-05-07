package securechat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 *
 * @author xuedong.wang
 * @date 2017-5-7
 */
public class SecureChatClientHandler extends SimpleChannelInboundHandler<String> {

    public void messageReceived(ChannelHandlerContext ctx, String msg) {
        System.err.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
