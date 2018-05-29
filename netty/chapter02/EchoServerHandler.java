package chapter02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/* 1.@Sharable ��ʶ�����ʵ��֮������� channel ���湲��
 * 2.��־��Ϣ���������̨
 * 3.�������յ���Ϣ���ظ������ߡ�ע�⣬�⻹û�г�ˢ����
 * 4.��ˢ���д�����Ϣ��Զ�̽ڵ㡣�ر�ͨ���󣬲������
 * 5.��ӡ�쳣��ջ����
 * 6.�ر�ͨ��
 */
@Sharable // 1
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
  // ÿ����Ϣ��վ�������
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    ByteBuf in = (ByteBuf) msg;
    System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
    // 2
    ctx.write(in); // 3
  }

  // ֪ͨ���������� channelRead() �ǵ�ǰ�������е����һ����Ϣʱ����
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)// 4
        .addListener(ChannelFutureListener.CLOSE);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace(); // 5
    ctx.close(); // 6
  }
}
