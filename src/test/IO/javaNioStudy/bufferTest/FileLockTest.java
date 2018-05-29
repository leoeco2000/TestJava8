package test.IO.javaNioStudy.bufferTest;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {

  public static void main(String[] args) throws Exception {
    RandomAccessFile fromFile = new RandomAccessFile("data/copy.txt", "rw");
    FileChannel fromChannel = fromFile.getChannel();
    // lock 方法中，start、end参数指定锁定文件的部分，
    // 第三个boolean参数，true：表示获取共享锁，false：表示获取排它锁；
    System.out.println("trying to lock file...");
    int start = 0;
    int end = 10;
    FileLock fileLock = fromChannel.lock(start, end, true);
    System.out.println(fileLock.isShared());
    long fileSize = fromChannel.size();
    System.out.println(fileSize);
    System.out.println("after lock");

    System.out.println("pause...");
    Thread.sleep(8000);

    System.out.println("releasing file lock");
    fileLock.release();
    System.out.println("after release.");

    fromChannel.close();
    fromFile.close();
  }

}
