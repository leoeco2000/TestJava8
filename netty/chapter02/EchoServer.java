package chapter02;

import java.net.InetSocketAddress;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


//���� ServerBootstrap ʵ��������������������
//����������һ�� NioEventLoopGroup ʵ���������¼��Ĵ���������µ����ӺͶ�/д���ݡ�
//ָ������ InetSocketAddress ����������
//ͨ�� EchoServerHandler ʵ����ÿһ���µ� Channel ��ʼ��
//������ ServerBootstrap.bind() �󶨷�����

/*
 * 1.���ö˿�ֵ���׳�һ�� NumberFormatException ����ö˿ڲ����ĸ�ʽ����ȷ��
 * 2.���з������� start() ����
 * 3.���� EventLoopGroup
 * 4.���� ServerBootstrap
 * 5.ָ��ʹ�� NIO �Ĵ��� Channel
 * 6.���� socket ��ַʹ����ѡ�Ķ˿�
 * 7.���EchoServerHandler �� Channel �� ChannelPipeline
 * 8.�󶨵ķ�����;sync �ȴ��������ر�
 * 9.�ر� channel �� �飬ֱ�������ر�
 * 10.�ر�EventLoopGroup���ͷ�������Դ��
 */
public class EchoServer {
  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
//    if (args.length != 1) {
//      System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
//      return;
//    }
//    int port = Integer.parseInt(args[0]); // 1
    int port = 8080;
    new EchoServer(port).start(); // 2
  }

  public void start() throws Exception {
    NioEventLoopGroup group = new NioEventLoopGroup(); // 3
    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(group) // 4
          .channel(NioServerSocketChannel.class) // 5
          .localAddress(new InetSocketAddress(port)) // 6
          // ���EchoServerHandler �� Channel �� ChannelPipeline
          .childHandler(new ChannelInitializer<SocketChannel>() { // 7
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new EchoServerHandler());
            }
          });
      // �󶨵ķ�����;sync �ȴ��������ر�
      ChannelFuture f = b.bind().sync(); // 8
      System.out.println(
          EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
      f.channel().closeFuture().sync(); // 9
    } finally {
      group.shutdownGracefully().sync(); // 10
    }
  }
}
