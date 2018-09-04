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
    // 从输入输出流中得到 channel
    FileChannel fIns = ins.getChannel();
    FileChannel fOut = out.getChannel();
    // 创建一个Buffer 大小为1024
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    while (true) {
      // 清空缓冲区
      buffer.clear();
      // 读输入到缓冲区
      int n = fIns.read(buffer);
      if (n == -1) {
        break;
      }
      // flip方法让缓冲区可以将新读入的数据写入另一个通道
      buffer.flip();
      // 从输出通道中将数据写入缓冲区
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
