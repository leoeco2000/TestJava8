package test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * Netty�У�ͨѶ��˫���������Ӻ󣬻�����ݰ���ByteBuf�ķ�ʽ���д��䣬
 * ����httpЭ���У�����ͨ��HttpRequestDecoder��ByteBuf���������д���ת����http�Ķ���
 * 
 */
public class SimpleServer {
  private int port;

  public SimpleServer(int port) {
    this.port = port;
  }

  public void run() throws Exception {
    // EventLoopGroup����������IO�����Ķ��߳��¼�ѭ����
    // bossGroup �������ս���������
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    // workerGroup ���������Ѿ������յ�����
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      // ���� NIO ����ĸ���������
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup)
          // ���� Channel
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
              // ע��handler
              ch.pipeline().addLast(new SimpleServerHandler());
            }
          }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

      // �󶨶˿ڣ���ʼ���ս���������
      ChannelFuture f = b.bind(port).sync();
      // �ȴ������� socket �ر� ��
      f.channel().closeFuture().sync();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws Exception {
    new SimpleServer(9999).run();
  }
}
