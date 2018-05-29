package test.IO.javaNioStudy.multiplexingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
  /* �������ݻ����� */
  private static ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
  /* �������ݻ����� */
  private static ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

  public static void main(String[] args) throws IOException {
    // ��socketͨ��
    SocketChannel socketChannel = SocketChannel.open();
    // ����Ϊ��������ʽ
    socketChannel.configureBlocking(false);
    // ��ѡ����
    Selector selector = Selector.open();
    // ע�����ӷ����socket����
    socketChannel.register(selector, SelectionKey.OP_CONNECT);
    // ����
    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
    // ���SocketChannel�ڷ�����ģʽ�£���ʱ����connect()���÷������������ӽ���֮ǰ�ͷ����ˡ�
    // Ϊ��ȷ�������Ƿ��������Ե���finishConnect()�ķ�������������
    // while(!socketChannel.finishConnect() ){
    // //wait, or do something else...
    // }
    Set<SelectionKey> selectionKeys;
    Iterator<SelectionKey> iterator;
    SelectionKey selectionKey;
    SocketChannel client;
    String receiveText;
    String sendText;
    int count = 0;

    while (true) {
      // ѡ��һ���������Ӧ��ͨ����Ϊ I/O ����׼��������
      // �˷���ִ�д�������ģʽ��ѡ�������
      selector.select();
      // ���ش�ѡ��������ѡ�������
      selectionKeys = selector.selectedKeys();
      // System.out.println(selectionKeys.size());
      iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        selectionKey = iterator.next();
        if (selectionKey.isConnectable()) {
          System.out.println("client connect");
          client = (SocketChannel) selectionKey.channel();
          // �жϴ�ͨ�����Ƿ����ڽ������Ӳ�����
          // ����׽���ͨ�������ӹ��̡�
          if (client.isConnectionPending()) {
            client.finishConnect();
            System.out.println("�������!");
            sendbuffer.clear();
            sendbuffer.put("Hello,Server".getBytes());
            sendbuffer.flip();
            client.write(sendbuffer);
          }
          client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
          client = (SocketChannel) selectionKey.channel();
          // ������������Ա��´ζ�ȡ
          receivebuffer.clear();
          // ��ȡ�����������������ݵ���������
          count = client.read(receivebuffer);
          if (count > 0) {
            receiveText = new String(receivebuffer.array(), 0, count);
            System.out.println("�ͻ��˽��ܷ�����������--:" + receiveText);
            client.register(selector, SelectionKey.OP_WRITE);
          }

        } else if (selectionKey.isWritable()) {
          sendbuffer.clear();
          client = (SocketChannel) selectionKey.channel();
          sendText = "message from client--";
          sendbuffer.put(sendText.getBytes());
          // ������������־��λ,��Ϊ������put�����ݱ�־���ı�Ҫ����ж�ȡ���ݷ��������,��Ҫ��λ
          sendbuffer.flip();
          client.write(sendbuffer);
          System.out.println("�ͻ�����������˷�������--��" + sendText);
          client.register(selector, SelectionKey.OP_READ);
        }
      }
      selectionKeys.clear();
    }
  }
}
