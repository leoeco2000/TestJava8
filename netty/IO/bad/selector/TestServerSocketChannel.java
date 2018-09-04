package IO.bad.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class TestServerSocketChannel {

  private static Selector selector = null;

  public static void main(String[] args) {

    try {
      // ����һ��ServerSocketChannel,����8080�˿ڣ�������ģʽ
      ServerSocketChannel ssc8080 = (new TestServerSocketChannel()).createServerSocketChannel(8080);
      // ����һ��ServerSocketChannel,����8090�˿ڣ�������ģʽ
      ServerSocketChannel ssc8090 = (new TestServerSocketChannel()).createServerSocketChannel(8090);
      // ����������
      selector = Selector.open();
      // ��ͨ��ע�������
      ssc8080.register(selector, SelectionKey.OP_ACCEPT);
      ssc8090.register(selector, SelectionKey.OP_ACCEPT);
      // �����̣߳������ͻ��˷��͹���������
      Thread thread = new Thread(new MyServerRunnable(selector));
      thread.start();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public ServerSocketChannel createServerSocketChannel(int port) {
    ServerSocketChannel ssc = null;
    try {
      ssc = ServerSocketChannel.open();
      ssc.socket().bind(new InetSocketAddress(port));
      ssc.configureBlocking(false);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ssc;
  }
}

class MyServerRunnable implements Runnable {
  private Selector selector;

  public MyServerRunnable(Selector s) {
    this.selector = s;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    while (true) {
      try {
        // ����3s���ж�selectorע���ͨ���Ƿ��о�����
        if (selector.select(3000) == 0) {
          System.out.println("���ڵȴ�����......");
          continue;
        } else {
          Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
          while (keys.hasNext()) {
            SelectionKey key = keys.next();
            // �ж��Ƿ����µ�����
            if (key.isAcceptable()) {
              HandleRequest.handleAccept(key);
            } else if (key.isReadable()) { // �ж��Ƿ��ж�����
              HandleRequest.handleRead(key);
            } else if (key.isValid() && key.isWritable()) { // �ж��Ƿ��д��������Ȥ
              HandleRequest.handleWrite(key);
            }
            keys.remove(); // �Ƴ�������ļ�
          }
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}

class HandleRequest {
  // �����µ�����ʱ
  public static void handleAccept(SelectionKey key) {
    try {
      // ͨ��SelectionKey����key����SocketChannel����
      SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
      // ����socketChannelΪ������ģʽ
      socketChannel.configureBlocking(false);
      // ��ͨ��ע��ѡ�����͸���Ȥ�¼�
      socketChannel.register(key.selector(), SelectionKey.OP_READ);
      // ������ݴӷ��������ĸ��˿ڴ���
      System.out.println("receive data from port:" + socketChannel.socket().getLocalPort());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void handleRead(SelectionKey key) {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    System.out.println("receive data from port:" + socketChannel.socket().getLocalPort());
    ByteBuffer buffer = ByteBuffer.allocate(32);
    try {
      int readBytes = socketChannel.read(buffer);
      // ������ݴ��ĸ��ͻ��˵�ַ����
      System.out.println("receive data from " + socketChannel.socket().getRemoteSocketAddress()
          + ", the data are ");
      // ��ȡ�������е�����
      while (readBytes != 0) {
        buffer.flip();
        String receiveString = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
        System.out.print(receiveString);
        buffer.clear();
        readBytes = socketChannel.read(buffer);
      }
      // ����ͨ������Ȥ���¼�Ϊ�����������͡�д������
      key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void handleWrite(SelectionKey key) {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    ByteBuffer writeBuffer = ByteBuffer.wrap("This message is from server".getBytes());
    try {
      socketChannel.write(writeBuffer);
      System.out.println("The message is writen in channel");
      key.interestOps(SelectionKey.OP_READ);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}