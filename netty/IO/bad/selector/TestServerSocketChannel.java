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
      // 创建一个ServerSocketChannel,监听8080端口，非阻塞模式
      ServerSocketChannel ssc8080 = (new TestServerSocketChannel()).createServerSocketChannel(8080);
      // 创建一个ServerSocketChannel,监听8090端口，非阻塞模式
      ServerSocketChannel ssc8090 = (new TestServerSocketChannel()).createServerSocketChannel(8090);
      // 创建监听器
      selector = Selector.open();
      // 向通道注册监听器
      ssc8080.register(selector, SelectionKey.OP_ACCEPT);
      ssc8090.register(selector, SelectionKey.OP_ACCEPT);
      // 开启线程，监听客户端发送过来的数据
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
        // 阻塞3s后判断selector注册的通道是否有就绪的
        if (selector.select(3000) == 0) {
          System.out.println("正在等待请求......");
          continue;
        } else {
          Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
          while (keys.hasNext()) {
            SelectionKey key = keys.next();
            // 判断是否有新的连接
            if (key.isAcceptable()) {
              HandleRequest.handleAccept(key);
            } else if (key.isReadable()) { // 判断是否有读操作
              HandleRequest.handleRead(key);
            } else if (key.isValid() && key.isWritable()) { // 判断是否对写操作感兴趣
              HandleRequest.handleWrite(key);
            }
            keys.remove(); // 移除处理过的键
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
  // 当有新的连接时
  public static void handleAccept(SelectionKey key) {
    try {
      // 通过SelectionKey对象key创建SocketChannel对象
      SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
      // 设置socketChannel为非阻塞模式
      socketChannel.configureBlocking(false);
      // 向通道注册选择器和感兴趣事件
      socketChannel.register(key.selector(), SelectionKey.OP_READ);
      // 输出数据从服务器的哪个端口传入
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
      // 输出数据从哪个客户端地址传入
      System.out.println("receive data from " + socketChannel.socket().getRemoteSocketAddress()
          + ", the data are ");
      // 读取缓冲区中的数据
      while (readBytes != 0) {
        buffer.flip();
        String receiveString = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
        System.out.print(receiveString);
        buffer.clear();
        readBytes = socketChannel.read(buffer);
      }
      // 更改通道感兴趣的事件为“读操作”和“写操作”
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