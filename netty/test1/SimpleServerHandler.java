package test1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("SimpleServerHandler.channelRead");
    ByteBuf result = (ByteBuf) msg;
    byte[] result1 = new byte[result.readableBytes()];
    // msg�д洢����ByteBuf���͵����ݣ������ݶ�ȡ��byte[]��
    result.readBytes(result1);
    String resultStr = new String(result1);
    // ���ղ���ӡ�ͻ��˵���Ϣ
    System.out.println("Client said:" + resultStr);
    // �ͷ���Դ�����кܹؼ�
    result.release();

    // ��ͻ��˷�����Ϣ
    String response = "hello client!";
    // �ڵ�ǰ�����£����͵����ݱ���ת����ByteBuf����
    ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
    encoded.writeBytes(response.getBytes());
    ctx.write(encoded);
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // �������쳣�͹ر�����
    cause.printStackTrace();
    ctx.close();
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }

}
