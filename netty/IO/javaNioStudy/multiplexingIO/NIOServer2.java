package IO.javaNioStudy.multiplexingIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author sanchan
 * @since 1.0
 */
public class NIOServer2 {
  public static void main(String[] args) {
    /**
     * �������� ������������ʱ����SelectionKey�Ĳ������ͽ����ڲ���Handler���д���
     */
    try {
      // ����ServerSocketChannel
      ServerSocketChannel ssc = ServerSocketChannel.open();
      // ���ü���8080�˿�
      ssc.socket().bind(new InetSocketAddress(8080));
      // ����Ϊ������ģʽ
      ssc.configureBlocking(false);
      // ΪServerSocketChannelע��Selector
      Selector selector = Selector.open();
      ssc.register(selector, SelectionKey.OP_ACCEPT);
      // ����Handler
      Handler handler = new Handler(1024);
      while (true) {
        // �ȴ�����,ÿ�εȴ�����3s,����3����̼߳�������,�������0��ʹ���޲����ط���,��һֱ����
        // һ��������Selectorע����һ������channel�󣬾Ϳ��Ե���select����ȡchannel��
        // select�����᷵�����д��ھ���״̬��channel��
        // select()�����ڷ���channel֮ǰ��������״̬��
        // select(long timeout)��select������һ������������������һ����ʱ���ơ�
        // selectNow()�������������ݵ�ǰ״̬���̷��غ��ʵ�channel��

        // wakeUp()
        // ���ڵ���select�����������̣߳�
        // ����ͨ������Selector.wakeup()�����Ѽ����ʱ��Ȼû��channel���ھ���״̬����������ǣ�������һ���̵߳���wakeup����������select�������߳̾ͻ����̷��ء�

        // close()
        // ������Selector��Ϻ���Ҫ����close������
        // close�ĵ��û�ر�Selector��ʹ��ص�SelectionKey����Ч��
        // channel�����ܱ��رա�
        if (selector.select(3000) == 0) {
          System.out.println("�ȴ�����ʱ~~~~~");
          continue;
        }
        System.out.println("��������~~~~~");
        // ��ȡ�ȴ������SelectionKey
        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
        while (keyIterator.hasNext()) {
          SelectionKey key = keyIterator.next();
          try {
            // ���ݲ�ͬ�������ѡ���Ӧ�Ĵ�����
            if (key.isAcceptable()) {
              handler.handleAccept(key);
            }
            if (key.isReadable()) {
              handler.handleRead(key);
            }
            keyIterator.remove();
            // ע��keyIterater.remove()�����ĵ��ã�
            // Selector���������Ƴ�SelectionKey����
            // ���������Ҫ�����յ�ִ�С����´�channel���ھ����ǣ�
            // Selector��Ȼ�����Щkey�ٴμ������
          } catch (IOException e) {
            e.printStackTrace();
            // ����쳣��˵�����ӽ���,�Ƴ�
            keyIterator.remove();
            continue;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }

  }

  /**
   * ��������
   */
  private static class Handler {
    private int bufferSize = 1024;
    private String localCharset = "UTF-8";

    public Handler() {}

    public Handler(int bufferSize) {
      this(bufferSize, null);
    }

    public Handler(String localCharset) {
      this(-1, localCharset);
    }

    public Handler(int bufferSize, String localCharset) {
      if (bufferSize > 0)
        this.bufferSize = bufferSize;
      if (localCharset != null)
        this.localCharset = localCharset;
    }

    /**
     * �����������
     *
     * @param key
     * @throws IOException
     */
    public void handleAccept(SelectionKey key) throws IOException {
      // ��ȡChannel
      // SelectionKey.channel���ص�channelʵ��
      // ��ҪǿתΪ����ʵ��ʹ�õľ����channel���ͣ�
      // ����ServerSocketChannel��SocketChannel.
      SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
      // ���÷�����
      sc.configureBlocking(false);
      // ע���������Selector
      sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    /**
     * ���������
     *
     * @param key
     * @throws IOException
     */
    public void handleRead(SelectionKey key) throws IOException {
      // ��ȡChannel
      SocketChannel sc = ((SocketChannel) key.channel());
      // ��ȡByteBuffer
      /**
       * Bufferר�����ڴ洢����,���ĸ���Ϊ��Ҫ������: 1. capacity:������ Buffer�����Ա���Ԫ�ص�����,����ʱ����,ʹ�ù����в����޸ġ� 2.
       * limit:����ʹ�õ����ޡ� �մ���Bufferʱlimit����capacity�������limit���á����ܳ���capacity��֮��,limit�ͳ������ɷ��ʵ�ֵ��
       * ����,һ��Buffer��capacityΪ100,��ʾ�����Ա���100������,ֻд��20��֮���Ҫ��ȡ,�ڶ�ȡʱlimit�ͻ�����Ϊ20�� 3.
       * position:��ǰ������Ԫ����������λ�á� position��0��ʼ,����get��put�����Զ����¡� 4. mark:������ʱ����position��ֵ��
       * position���浽mark֮��Ϳ����޸Ĳ�������صĲ���,������ɺ����ͨ��reset������mark��ֵ�ָ���position��
       * markĬ��ֵΪ-1,����ֵ����С��position��ֵ��
       * ����,Buffer��һ��������20������,positionΪ10,�������ȡ15��20֮�������,��ʱ�Ϳ��Ե���Buffer��mark������Ŀǰ��position���浽mark��,Ȼ�����Buffer��position(15)��positionָ���15��Ԫ��,��ʱ�Ϳ��Զ�ȡ����ȡ���֮��ʹ��Buffer��reset�Ϳ��Խ�position�ָ���10.
       * �������Buffer��position����ʱ�����ֵС��mark��ǰ��ֵ,��Ὣmark��Ϊ-1��
       */
      ByteBuffer buffer = (ByteBuffer) key.attachment();
      // ����ByteBuffer������limit=capacity��position=0��mark=-1
      buffer.clear();
      buffer.reset();
      // û�л�ȡ��������ر�
      if (sc.read(buffer) == -1) {
        sc.close();
      } else {
        /**
         * flip()����: �ڱ�������ʱ����һ������position��1,������ɺ�Ҫ��ȡ���� �͵�����limit=position��position=0
         **/
        buffer.flip();
        // �������ݵ��ͻ���
        String receivedString =
            Charset.forName(localCharset).newDecoder().decode(buffer).toString();
        System.out.println("�ӿͻ��˻�ȡ��������:" + receivedString);
        String sendString = "������Ѿ���ȡ��������:" + receivedString;
        buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
        sc.write(buffer);
        // �ر�SocketChannel
        sc.close();
      }

    }
  }
}
