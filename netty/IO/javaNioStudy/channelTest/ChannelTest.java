package IO.javaNioStudy.channelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
  public static void main(String[] args) throws IOException {
    // System.out.println(ChannelTest.class.getClassLoader().getResource("paste.txt"));
    // String copyPath = ChannelTest.class.getClassLoader().getResource("copy.txt").getPath();
    // String pastePath = ChannelTest.class.getClassLoader().getResource("paste.txt").getPath();

    // System.out.println(ChannelTest.class.getResource("/paste.txt"));
    // String copyPath = ChannelTest.class.getResource("/copy.txt").getPath();
    // String pastePath = ChannelTest.class.getResource("/paste.txt").getPath();

    String copyPath = "data/copy.txt";
    String pastePath = "data/paste.txt";

    FileInputStream ins = new FileInputStream(new File(copyPath));
    FileOutputStream out = new FileOutputStream(new File(pastePath), true);
    // ������������еõ� channel
    FileChannel fIns = ins.getChannel();
    FileChannel fOut = out.getChannel();
    // ����һ��Buffer ��СΪ1024
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    while (true) {
      // ��ջ�����
      buffer.clear();
      // �����뵽������
      int n = fIns.read(buffer);
      if (n == -1) {
        break;
      }
      // flip�����û��������Խ��¶��������д����һ��ͨ��
      buffer.flip();
      // �����ͨ���н�����д�뻺����
      fOut.write(buffer);
      System.out.println(n);

    }

    fIns.close();
    fOut.close();


    out.flush();
    System.out.println("over !");
    ins.close();
    out.close();
  }

}
