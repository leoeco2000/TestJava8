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
     * 启动监听 当监听到请求时根据SelectionKey的操作类型交给内部类Handler进行处理
     */
    try {
      // 创建ServerSocketChannel
      ServerSocketChannel ssc = ServerSocketChannel.open();
      // 设置监听8080端口
      ssc.socket().bind(new InetSocketAddress(8080));
      // 设置为非阻塞模式
      ssc.configureBlocking(false);
      // 为ServerSocketChannel注册Selector
      Selector selector = Selector.open();
      ssc.register(selector, SelectionKey.OP_ACCEPT);
      // 创建Handler
      Handler handler = new Handler(1024);
      while (true) {
        // 等待请求,每次等待阻塞3s,超过3秒后线程继续运行,如果传入0或使用无参重载方法,将一直阻塞
        // 一旦我们向Selector注册了一个或多个channel后，就可以调用select来获取channel。
        // select方法会返回所有处于就绪状态的channel。
        // select()方法在返回channel之前处于阻塞状态。
        // select(long timeout)和select做的事一样，不过他的阻塞有一个超时限制。
        // selectNow()不会阻塞，根据当前状态立刻返回合适的channel。

        // wakeUp()
        // 由于调用select而被阻塞的线程，
        // 可以通过调用Selector.wakeup()来唤醒即便此时已然没有channel处于就绪状态。具体操作是，在另外一个线程调用wakeup，被阻塞与select方法的线程就会立刻返回。

        // close()
        // 当操作Selector完毕后，需要调用close方法。
        // close的调用会关闭Selector并使相关的SelectionKey都无效。
        // channel本身不管被关闭。
        if (selector.select(3000) == 0) {
          System.out.println("等待请求超时~~~~~");
          continue;
        }
        System.out.println("处理请求~~~~~");
        // 获取等待处理的SelectionKey
        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
        while (keyIterator.hasNext()) {
          SelectionKey key = keyIterator.next();
          try {
            // 根据不同请求操作选择对应的处理方法
            if (key.isAcceptable()) {
              handler.handleAccept(key);
            }
            if (key.isReadable()) {
              handler.handleRead(key);
            }
            keyIterator.remove();
            // 注意keyIterater.remove()方法的调用，
            // Selector本身并不会移除SelectionKey对象，
            // 这个操作需要我们收到执行。当下次channel处于就绪是，
            // Selector任然会把这些key再次加入进来
          } catch (IOException e) {
            e.printStackTrace();
            // 如果异常就说明连接结束,移除
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
   * 请求处理类
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
     * 处理请求操作
     *
     * @param key
     * @throws IOException
     */
    public void handleAccept(SelectionKey key) throws IOException {
      // 获取Channel
      // SelectionKey.channel返回的channel实例
      // 需要强转为我们实际使用的具体的channel类型，
      // 例如ServerSocketChannel或SocketChannel.
      SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
      // 设置非阻塞
      sc.configureBlocking(false);
      // 注册读操作的Selector
      sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    /**
     * 处理读操作
     *
     * @param key
     * @throws IOException
     */
    public void handleRead(SelectionKey key) throws IOException {
      // 获取Channel
      SocketChannel sc = ((SocketChannel) key.channel());
      // 获取ByteBuffer
      /**
       * Buffer专门用于存储数据,有四个极为重要的属性: 1. capacity:容量。 Buffer最多可以保存元素的数量,创建时设置,使用过程中不可修改。 2.
       * limit:可以使用的上限。 刚创建Buffer时limit等于capacity。如果给limit设置【不能超过capacity】之后,limit就成了最大可访问的值。
       * 例如,一个Buffer的capacity为100,表示最多可以保存100个数据,只写入20个之后就要读取,在读取时limit就会设置为20。 3.
       * position:当前所操作元素所在索引位置。 position从0开始,随着get和put方法自动更新。 4. mark:用来暂时保存position的值。
       * position保存到mark之后就可以修改并进行相关的操作,操作完成后可以通过reset方法将mark的值恢复到position。
       * mark默认值为-1,且其值必须小于position的值。
       * 例如,Buffer中一共保存了20个数据,position为10,现在想读取15到20之间的数据,这时就可以调用Buffer的mark方法将目前的position保存到mark中,然后调用Buffer的position(15)将position指向第15个元素,这时就可以读取。读取完成之后使用Buffer的reset就可以将position恢复到10.
       * 如果调用Buffer的position方法时传入的值小于mark当前的值,则会将mark设为-1。
       */
      ByteBuffer buffer = (ByteBuffer) key.attachment();
      // 重置ByteBuffer。设置limit=capacity、position=0、mark=-1
      buffer.clear();
      buffer.reset();
      // 没有获取到内容则关闭
      if (sc.read(buffer) == -1) {
        sc.close();
      } else {
        /**
         * flip()作用: 在保存数据时保存一个数据position加1,保存完成后要读取数据 就得设置limit=position，position=0
         **/
        buffer.flip();
        // 返回数据到客户端
        String receivedString =
            Charset.forName(localCharset).newDecoder().decode(buffer).toString();
        System.out.println("从客户端获取到了数据:" + receivedString);
        String sendString = "服务端已经获取到了数据:" + receivedString;
        buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
        sc.write(buffer);
        // 关闭SocketChannel
        sc.close();
      }

    }
  }
}
