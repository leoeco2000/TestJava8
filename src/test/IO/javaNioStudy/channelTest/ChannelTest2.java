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
    // get 输出流
    FileOutputStream out = new FileOutputStream(new File(destPath));
    // 得到channel
    FileChannel fOut = out.getChannel();
    // 创建一个Buffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // put 进去
    // for (int i = 0; i < metas.length; i++) {
    // buffer.put(metas[i].getBytes());
    // }
    buffer.put(metas);
    // 切换Buffer的模式到写
    buffer.flip();
    // 写入
    fOut.write(buffer);
    // close
    fOut.close();
    out.close();

  }
}
