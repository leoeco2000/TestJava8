package test.IO.bad.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestSocketChannel {
  public static void main(String[] args){
      SocketChannel socketChannel;
      try {
          //创建SocketChannel
          socketChannel = SocketChannel.open();
          //连接到某台服务器的某个端口
          socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
          String sendString = "This is a message from client, Please read it carefully. Thanke you very much";
          ByteBuffer buffer = ByteBuffer.wrap(sendString.getBytes());
          socketChannel.write(buffer);
          //关闭通道
          socketChannel.close();
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
}