package test.IO.javaNioStudy.bufferTest;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
  public static void main(String[] args) {
    // 创建一个缓冲区 大小是10
    ByteBuffer buffer = ByteBuffer.allocate(100);
    // 存入数据 1-9
    for (int i = 0; i < 10; i++) {
      buffer.put((byte) i);
    }
    // 得到readOnly buffer
    ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
    // 这句会报错！
    readOnlyBuffer.put(1, (byte) 11);
  }
}
