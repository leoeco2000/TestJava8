package IO.javaNioStudy.multiplexingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

  /* �������ݻ����� */
  private ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
  /* �������ݻ����� */
  private ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

  private Selector selector;

  public NIOServer(int port) throws IOException {
    // �򿪷������׽���ͨ��
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    // ����������Ϊ������
    serverSocketChannel.configureBlocking(false);
    // �������ͨ�������ķ������׽���
    ServerSocket serverSocket = serverSocketChannel.socket();
    // ���з���İ�
    serverSocket.bind(new InetSocketAddress(port));
    // ͨ��open()�����ҵ�Selector
    selector = Selector.open();
    // ע�ᵽselector���ȴ�����
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("Server Start----:");
  }

  private void listen() throws IOException {
    while (true) {
      // �ȴ����󣬴���0���߲���������һֱ����
      // ��ע���¼�����ʱ���������أ�����÷�����һֱ����

      // һ����Selectorע����һ����ͨ�����Ϳ��Ե��ü������ص�select()������
      // ��Щ����������������Ȥ���¼��������ӡ����ܡ�����д���Ѿ�׼����������Щͨ����
      // ���仰˵�������ԡ�����������ͨ������Ȥ��
      // select()�����᷵�ض��¼��Ѿ���������Щͨ��
      // select()������������һ��ͨ������ע����¼��Ͼ����ˡ�

      // select()�������ص�intֵ��ʾ�ж���ͨ���Ѿ�������
      // �༴�����ϴε���select()�������ж���ͨ����ɾ���״̬��
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        // selectionKey.channel();
        // selectionKey.selector();
        iterator.remove();
        handleKey(selectionKey);
      }
    }
  }

  private void handleKey(SelectionKey selectionKey) throws IOException {
    // ��������
    ServerSocketChannel server = null;
    SocketChannel client = null;
    String receiveText;
    String sendText;
    int count = 0;
    // ���Դ˼���ͨ���Ƿ���׼���ý����µ��׽������ӡ�
    // �������SelectionKey��Ӧ��ͨ���Ƿ��Ѿ�������һ���µ�Socket���ӡ�
    if (selectionKey.isAcceptable()) {
      // ����������ص���ע�����SelectionKey��channel
      // (�ͻ����ŵ�,��������׽���ͨ��)�����������ServerSocketChannel
      // Ҳ����˵,��Щͨ��ע������key,ͨ��key�Ϳ���ȡ����Щͨ��
      // ת�ͳ���Ҫ��������ͣ���ServerSocketChannel��SocketChannel�ȡ�
      server = (ServerSocketChannel) selectionKey.channel();

      // ���ܵ���ͨ���׽��ֵ���������
      // ͨ�� ServerSocketChannel.accept() ���������½��������ӡ�
      // �� accept()�������ص�ʱ��,������һ�������½��������ӵ� SocketChannel��
      // ���, accept()������һֱ�������������ӵ��
      // ServerSocketChannel�������óɷ�����ģʽ��
      client = server.accept();

      // ����Ϊ������
      // �ڷ�����ģʽ�£�accept() ���������̷��أ�
      // �����û���½���������,���صĽ���null��
      // ��ˣ���Ҫ��鷵�ص�SocketChannel�Ƿ���null
      client.configureBlocking(false);
      // ע�ᵽselector���ȴ�����
      // һ��ͨ�����Ա�ע�ᵽ���ѡ�����ϣ�����ÿ��ѡ��������ֻ�ܱ�ע��һ�Ρ�
      client.register(selector, SelectionKey.OP_READ);
      // ���Խ�һ��������߸�����Ϣ���ŵ�SelectionKey�ϣ�
      // �������ܷ����ʶ��ĳ��������ͨ����
      // ���磬���Ը�����ͨ��һ��ʹ�õ�Buffer�����ǰ����ۼ����ݵ�ĳ������ʹ�÷������£�
      // selectionKey.attach(theObject);
      // Object attachedObj = selectionKey.attachment();
      // ����������register()������Selectorע��Channel��ʱ�򸽼Ӷ����磺
      // SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
    } else if (selectionKey.isReadable()) {
      // ����Ϊ֮�����˼���ͨ����
      client = (SocketChannel) selectionKey.channel();
      // ������������Ա��´ζ�ȡ
      receivebuffer.clear();
      // ��ȡ�����������������ݵ���������
      // �÷��������ݴ�SocketChannel ����Buffer�С�
      // ������ģʽ��,read()��������δ��ȡ���κ�����ʱ���ܾͷ����ˡ�
      // read()�������ص�intֵ��ʾ���˶����ֽڽ�Buffer�
      // ������ص���-1����ʾ�Ѿ�����������ĩβ�����ӹر��ˣ���
      count = client.read(receivebuffer);
      if (count > 0) {
        receiveText = new String(receivebuffer.array(), 0, count);
        System.out.println("�������˽��ܿͻ�������--:" + receiveText);
        client.register(selector, SelectionKey.OP_WRITE);
      }
    } else if (selectionKey.isWritable()) {
      // ������������Ա��´�д��
      sendbuffer.clear();
      // ����Ϊ֮�����˼���ͨ����
      client = (SocketChannel) selectionKey.channel();
      sendText = "message from server--";
      // �򻺳�������������
//      while (sendbuffer.hasRemaining()) {
        sendbuffer.put(sendText.getBytes());
//      }
      // ������������־��λ,��Ϊ������put������,��־���ı�,Ҫ����ж�ȡ���ݷ��������,��Ҫ��λ
      sendbuffer.flip();
      // �����ͨ��
      // ע��SocketChannel.write()�����ĵ�������һ��whileѭ���еġ�
      // ������ģʽ�£�write()��������δд���κ�����ʱ���ܾͷ����ˡ�
      // Write()�����޷���֤��д�����ֽڵ�SocketChannel��
      // ���ԣ������ظ�����write()ֱ��Bufferû��Ҫд���ֽ�Ϊֹ��
      client.write(sendbuffer);
      System.out.println("����������ͻ��˷�������--��" + sendText);
      client.register(selector, SelectionKey.OP_READ);
    }
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    int port = 8080;
    NIOServer server = new NIOServer(port);
    server.listen();
  }
}
