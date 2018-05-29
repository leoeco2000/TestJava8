package test.IO.bad.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
  public static void main(String[] args){
      try {
          //创建一个ServerSocketChannel
          ServerSocketChannel ssc = ServerSocketChannel.open();
          //监听8080端口
          ssc.socket().bind(new InetSocketAddress(8080));
          SocketChannel socketChannel = ssc.accept();  //阻塞，开始监听8080端口
          ByteBuffer buffer = ByteBuffer.allocate(100); //设置buffer的capacity为100
          int readBytes = socketChannel.read(buffer);  //利用channel将数据写入buffer
          while(readBytes != -1){
              buffer.flip();  //切换为读模式
              while(buffer.hasRemaining()){ //检查buffer是否读完
                  System.out.print((char)buffer.get());  //1byte的读数据
              }
              buffer.clear();  //清空buffer缓冲区
              readBytes = socketChannel.read(buffer);
          }
          socketChannel.close(); //关闭socketChannel
          ssc.close();  //关闭ServerSocketChannel
          System.out.println("It it over");
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
}