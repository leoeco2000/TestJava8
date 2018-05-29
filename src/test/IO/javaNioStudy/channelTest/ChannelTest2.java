package test.IO.javaNioStudy.channelTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest2 {
  public static void main(String[] args) throws IOException {
    byte[] metas = String.valueOf(Math.PI).getBytes();
    System.out.println(String.valueOf(Math.PI));
    // String[] metas = {"hello world", "file channel"};
    String destPath = "data/dest.txt";
    // get �����
    FileOutputStream out = new FileOutputStream(new File(destPath));
    // �õ�channel
    FileChannel fOut = out.getChannel();
    // ����һ��Buffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // put ��ȥ
    // for (int i = 0; i < metas.length; i++) {
    // buffer.put(metas[i].getBytes());
    // }
    buffer.put(metas);
    // �л�Buffer��ģʽ��д
    buffer.flip();
    // д��
    fOut.write(buffer);
    // close
    fOut.close();
    out.close();

  }
}
