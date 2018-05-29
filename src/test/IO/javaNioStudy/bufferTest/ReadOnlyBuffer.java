package test.IO.javaNioStudy.bufferTest;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
  public static void main(String[] args) {
    // ����һ�������� ��С��10
    ByteBuffer buffer = ByteBuffer.allocate(100);
    // �������� 1-9
    for (int i = 0; i < 10; i++) {
      buffer.put((byte) i);
    }
    // �õ�readOnly buffer
    ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
    // ���ᱨ��
    readOnlyBuffer.put(1, (byte) 11);
  }
}
