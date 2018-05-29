package test.IO.bad.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
  public static void main(String[] args){
      try {
          //����һ��ServerSocketChannel
          ServerSocketChannel ssc = ServerSocketChannel.open();
          //����8080�˿�
          ssc.socket().bind(new InetSocketAddress(8080));
          SocketChannel socketChannel = ssc.accept();  //��������ʼ����8080�˿�
          ByteBuffer buffer = ByteBuffer.allocate(100); //����buffer��capacityΪ100
          int readBytes = socketChannel.read(buffer);  //����channel������д��buffer
          while(readBytes != -1){
              buffer.flip();  //�л�Ϊ��ģʽ
              while(buffer.hasRemaining()){ //���buffer�Ƿ����
                  System.out.print((char)buffer.get());  //1byte�Ķ�����
              }
              buffer.clear();  //���buffer������
              readBytes = socketChannel.read(buffer);
          }
          socketChannel.close(); //�ر�socketChannel
          ssc.close();  //�ر�ServerSocketChannel
          System.out.println("It it over");
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
}