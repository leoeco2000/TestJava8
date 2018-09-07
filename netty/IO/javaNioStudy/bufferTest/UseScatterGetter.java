package IO.javaNioStudy.bufferTest;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

// »±…Ÿclient
public class UseScatterGetter {
  static private final int HEADER_LENGTH = 20;
  static private final int BODY_LENGTH = 40;

  public static void main(String[] args) throws Exception {
    int port = 8080;
    ServerSocketChannel ssc = ServerSocketChannel.open();
    InetSocketAddress address = new InetSocketAddress(port);
    ssc.socket().bind(address);

    int messageLength = HEADER_LENGTH + BODY_LENGTH;

    ByteBuffer buffers[] = new ByteBuffer[2];
    buffers[0] = ByteBuffer.allocate(HEADER_LENGTH);
    buffers[1] = ByteBuffer.allocate(BODY_LENGTH);

    SocketChannel sc = ssc.accept();

    int byteRead = 0;
    while (byteRead < messageLength) {
      long r = sc.read(buffers);
      byteRead += r;

      System.out.println(" r : " + r);
      for (int i = 0; i < buffers.length; i++) {
        ByteBuffer bb = buffers[i];
        System.out.println("b" + i + " position : " + bb.position());
      }
    }

    // process message

    // flip buffers
    for (int i = 0; i < buffers.length; i++) {
      ByteBuffer bb = buffers[i];
      bb.flip();
    }

    // scatter write
    long byteWrite = 0;
    while (byteWrite < messageLength) {
      long r = sc.write(buffers);
      byteWrite += r;
    }

    // clear buffers
    for (int i = 0; i < buffers.length; i++) {
      ByteBuffer bb = buffers[i];
      bb.clear();
    }

    System.out.println(byteRead + "  " + byteWrite + " " + messageLength);
  }
}
