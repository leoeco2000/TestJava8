package test.IO.bad.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class TestSocketChannel {

  public static void main(String[] args) {
    try {
      // ����SocketChannel������192.168.6.42��������8080�˿�
      SocketChannel sc8080 = TestSocketChannel.createSocketChannel("127.0.0.1", 8080);

      // ����SocketChannel������192.168.6.42��������8090�˿�
      SocketChannel sc8090 = TestSocketChannel.createSocketChannel("127.0.0.1", 8090);

      // ����selector
      Selector selector = Selector.open();
      // ��ͨ��ע��ѡ������������selector����Channelʱ�Զ���������Ȥ
      sc8080.register(selector, SelectionKey.OP_READ);
      sc8090.register(selector, SelectionKey.OP_READ);
      // �����̣߳������Ƿ�ӷ������������ݴ�����
      Thread thread = new Thread(new MyRunnable(selector));
      thread.start();
      // �ֱ����������8080��8090�˿ڷ�������
      sendString(sc8080, "This message is going to send to server 8080 port");
      sendString(sc8090, "This message is going to send to server 8090 port");

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void sendString(SocketChannel sc, String str) {
    ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
    try {
      buffer.flip();
      // ��buffer�е�����д��scͨ��
      sc.write(buffer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ����һ��SocketChannel������ָ�����ӵ�IP��ַ�Ͷ˿ں�
   */
  public static SocketChannel createSocketChannel(String ip, int port) {
    SocketChannel socketChannel = null;
    try {
      // ����SocketChannel
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false); // ����Ϊ������ģʽ
      // ���ӵ�ĳ̨��������ĳ���˿�
      socketChannel.connect(new InetSocketAddress(ip, port));
      // �ж��Ƿ�������ɣ���δ�����ȴ�����
      while (!socketChannel.finishConnect()) {
//        System.out.println("It is connecting>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">");
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // ������ɷ��ظ�SocketChannel
    return socketChannel;
  }
}


class MyRunnable implements Runnable {
  private Selector selector;

  public MyRunnable(Selector s) {
    this.selector = s;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    try {
      while (true) {
        // ����2000ms���ж��Ƿ���ͨ����ע����¼��Ͼ����ˣ��������÷���ֵ����ͨ���ĸ���
        if (selector.select(2000) == 0) {
          System.out.println("please waiting.....");
          continue;
        } else {
          // ����ͨ������ʱ����ȡSelectionKey��������
          Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
          while (keys.hasNext()) {
            SelectionKey key = keys.next();
            // �ж�ͨ�����Ƿ�ɶ��¼������ˣ��������isReadable()��������TRUE
            if (key.isReadable()) {
              SocketChannel socketChannel = (SocketChannel) key.channel();
              // Ĭ�Ϸ������˷��͵����ݶ�С��1024byte�����һ�ο��Զ���
              ByteBuffer buffer = ByteBuffer.allocate(1024);
              socketChannel.read(buffer); // ����ͨ�������ݶ���buffer��
              buffer.flip(); // ��buffer�л�Ϊ��ģʽ
              String receiveString =
                  Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
              System.out.println(receiveString);
              buffer.clear(); // ��ջ�����buffer
            }
            // ����ͨ����ʲôʱ�����Ȥ���������Ƕԡ������͡�д������Ȥ
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            // �Ƴ���ǰ�Ѿ��������SelectionKey
            keys.remove();
          }
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
