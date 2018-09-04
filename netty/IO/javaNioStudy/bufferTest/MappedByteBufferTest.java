package IO.javaNioStudy.bufferTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
  public static void main(String[] args) throws IOException {
    // 得到文件所在路径
    String filePath = "data/d";
    File file = new File(filePath);
    FileInputStream ins = new FileInputStream(file);
    // 得到通道 FileChannel
    FileChannel fIns = ins.getChannel();
    /*
     * 内存映射文件，映射的大小是从0，到file.length()这么大
     */
    MappedByteBuffer mbb = fIns.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
    // 读数据到 data中
    byte[] data = new byte[(int) file.length()];
    int index = 0;
    while (mbb.hasRemaining()) {
      data[index] = mbb.get();
      index++;
    }
    // 输出
    System.out.println(new String(data));
    // close
    fIns.close();
    ins.close();
  }
}
