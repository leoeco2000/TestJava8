package test.IO.javaNioStudy.bufferTest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {
  public static void main(String[] args){
      readFromChannel();   //��channel��ȡ���ݵ�Buffer��
      readFromPut();   //put������������
  }
  public static void readFromChannel(){
      try {
          RandomAccessFile aFile = new RandomAccessFile("data/byte.txt","rw");
          FileChannel channel = aFile.getChannel();
          ByteBuffer buffer = ByteBuffer.allocate(64);  //����buffer�������Ĵ�С
          int bytesRead = channel.read(buffer);   //read to buffer
          while(bytesRead != -1){
              System.out.println("write mode position is " + buffer.position());
              System.out.println("write mode limit is " + buffer.limit());
              buffer.flip();  //�л�����ģʽ��limit=posit��position=0��
              System.out.println("Read mode position is " + buffer.position());
              System.out.println("Read mode limit is " + buffer.limit());
              while(buffer.hasRemaining()){
                  System.out.print((char)buffer.get());  //1byte�Ķ�����
              }
              System.out.println();
              buffer.clear();  //��position����Ϊ0��limit���ó�capacityֵ
              bytesRead = channel.read(buffer);
              aFile.close();
          }
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
  public static void readFromPut(){
      ByteBuffer buffer = ByteBuffer.allocate(48);
      for(int i = 0; i < 12; i++){
          buffer.putInt(i);
      }
      buffer.flip();
      while(buffer.hasRemaining()){
          System.out.print(buffer.get());
      }
  }
}
