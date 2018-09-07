package IO.javaNioStudy.bufferTest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class transferFromToTest {
  public static void main(String[] args) throws IOException {

    RandomAccessFile fromFile = new RandomAccessFile("data/copy.txt", "rw");
    RandomAccessFile inFile = new RandomAccessFile("data/paste.txt", "rw");

    FileChannel fromChannel = fromFile.getChannel();
    FileChannel inChannel = inFile.getChannel();

    int position = 0;
    long count = fromChannel.size();
    fromChannel.truncate(10);

    long successCount = inChannel.transferFrom(fromChannel, position, count);
    // long successCount = fromChannel.transferTo(position, count, inChannel);

    inChannel.force(true);
    System.out.println(successCount);
  }
}
