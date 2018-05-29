package test.IO.javaNioStudy.bufferTest;

import java.nio.ByteBuffer;

public class BufferSlice {
  public static void main(String[] args) {
    // ����һ�������� ��С��10
    ByteBuffer buffer = ByteBuffer.allocate(10);
    // �������� 1-10
    for (int i = 0; i < buffer.capacity(); i++) {
      buffer.put((byte) i);
    }
    // ����һ����������Ƭ ��4-7
    buffer.position(4);
    buffer.limit(7);
    ByteBuffer sub = buffer.slice();
    // ��4,5,6���� 11
    for (int i = 0; i < sub.capacity(); i++) {
      byte b = sub.get(i);
      sub.put((byte) (b * 11));
    }

    buffer.position(0);
    buffer.limit(buffer.capacity());

    while (buffer.hasRemaining()) {
      byte b = buffer.get();
      System.out.println(b);
    }
  }
}
