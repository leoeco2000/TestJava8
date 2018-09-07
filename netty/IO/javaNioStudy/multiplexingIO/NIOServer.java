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

  /* 发送数据缓冲区 */
  private ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
  /* 接受数据缓冲区 */
  private ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

  private Selector selector;

  public NIOServer(int port) throws IOException {
    // 打开服务器套接字通道
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    // 服务器配置为非阻塞
    serverSocketChannel.configureBlocking(false);
    // 检索与此通道关联的服务器套接字
    ServerSocket serverSocket = serverSocketChannel.socket();
    // 进行服务的绑定
    serverSocket.bind(new InetSocketAddress(port));
    // 通过open()方法找到Selector
    selector = Selector.open();
    // 注册到selector，等待连接
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("Server Start----:");
  }

  private void listen() throws IOException {
    while (true) {
      // 等待请求，传入0或者不传参数将一直阻塞
      // 当注册事件到达时，方法返回，否则该方法会一直阻塞

      // 一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。
      // 这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道。
      // 换句话说，如果你对“读就绪”的通道感兴趣，
      // select()方法会返回读事件已经就绪的那些通道
      // select()阻塞到至少有一个通道在你注册的事件上就绪了。

      // select()方法返回的int值表示有多少通道已经就绪。
      // 亦即，自上次调用select()方法后有多少通道变成就绪状态。
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
    // 接受请求
    ServerSocketChannel server = null;
    SocketChannel client = null;
    String receiveText;
    String sendText;
    int count = 0;
    // 测试此键的通道是否已准备好接受新的套接字连接。
    // 测试这个SelectionKey对应的通道是否已经接受了一个新的Socket连接。
    if (selectionKey.isAcceptable()) {
      // 这个方法返回的是注册这个SelectionKey的channel
      // (客户端信道,或服务器套接字通道)这里是上面的ServerSocketChannel
      // 也就是说,哪些通道注册过这个key,通过key就可以取到这些通道
      // 转型成你要处理的类型，如ServerSocketChannel或SocketChannel等。
      server = (ServerSocketChannel) selectionKey.channel();

      // 接受到此通道套接字的连接请求。
      // 通过 ServerSocketChannel.accept() 方法监听新进来的连接。
      // 当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。
      // 因此, accept()方法会一直阻塞到有新连接到达。
      // ServerSocketChannel可以设置成非阻塞模式。
      client = server.accept();

      // 配置为非阻塞
      // 在非阻塞模式下，accept() 方法会立刻返回，
      // 如果还没有新进来的连接,返回的将是null。
      // 因此，需要检查返回的SocketChannel是否是null
      client.configureBlocking(false);
      // 注册到selector，等待连接
      // 一个通道可以被注册到多个选择器上，但对每个选择器而言只能被注册一次。
      client.register(selector, SelectionKey.OP_READ);
      // 可以将一个对象或者更多信息附着到SelectionKey上，
      // 这样就能方便的识别某个给定的通道。
      // 例如，可以附加与通道一起使用的Buffer，或是包含聚集数据的某个对象。使用方法如下：
      // selectionKey.attach(theObject);
      // Object attachedObj = selectionKey.attachment();
      // 还可以在用register()方法向Selector注册Channel的时候附加对象。如：
      // SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
    } else if (selectionKey.isReadable()) {
      // 返回为之创建此键的通道。
      client = (SocketChannel) selectionKey.channel();
      // 将缓冲区清空以备下次读取
      receivebuffer.clear();
      // 读取服务器发送来的数据到缓冲区中
      // 该方法将数据从SocketChannel 读到Buffer中。
      // 非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。
      // read()方法返回的int值表示读了多少字节进Buffer里。
      // 如果返回的是-1，表示已经读到了流的末尾（连接关闭了）。
      count = client.read(receivebuffer);
      if (count > 0) {
        receiveText = new String(receivebuffer.array(), 0, count);
        System.out.println("服务器端接受客户端数据--:" + receiveText);
        client.register(selector, SelectionKey.OP_WRITE);
      }
    } else if (selectionKey.isWritable()) {
      // 将缓冲区清空以备下次写入
      sendbuffer.clear();
      // 返回为之创建此键的通道。
      client = (SocketChannel) selectionKey.channel();
      sendText = "message from server--";
      // 向缓冲区中输入数据
//      while (sendbuffer.hasRemaining()) {
        sendbuffer.put(sendText.getBytes());
//      }
      // 将缓冲区各标志复位,因为向里面put了数据,标志被改变,要想从中读取数据发向服务器,就要复位
      sendbuffer.flip();
      // 输出到通道
      // 注意SocketChannel.write()方法的调用是在一个while循环中的。
      // 非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。
      // Write()方法无法保证能写多少字节到SocketChannel。
      // 所以，我们重复调用write()直到Buffer没有要写的字节为止。
      client.write(sendbuffer);
      System.out.println("服务器端向客户端发送数据--：" + sendText);
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
