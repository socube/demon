package protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description 处理器
 * @Author xuedong.wang
 * @Date 17/5/8.
 */
public class ProtocolClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object obj)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Server->Client:"+incoming.remoteAddress()+obj.toString());

        if(obj instanceof ProtocolMsg) {
            ProtocolMsg msg = (ProtocolMsg)obj;
            System.out.println("Server->Client:"+incoming.remoteAddress()+msg.getBody());
        }
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.flush();
    }

}
