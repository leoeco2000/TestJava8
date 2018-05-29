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
      // 创建SocketChannel，连接192.168.6.42服务器的8080端口
      SocketChannel sc8080 = TestSocketChannel.createSocketChannel("127.0.0.1", 8080);

      // 创建SocketChannel，连接192.168.6.42服务器的8090端口
      SocketChannel sc8090 = TestSocketChannel.createSocketChannel("127.0.0.1", 8090);

      // 创建selector
      Selector selector = Selector.open();
      // 向通道注册选择器，并设置selector监听Channel时对读操作感兴趣
      sc8080.register(selector, SelectionKey.OP_READ);
      sc8090.register(selector, SelectionKey.OP_READ);
      // 启动线程，监听是否从服务器端有数据传过来
      Thread thread = new Thread(new MyRunnable(selector));
      thread.start();
      // 分别向服务器的8080和8090端口发送数据
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
      // 将buffer中的数据写入sc通道
      sc.write(buffer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 创建一个SocketChannel，其中指定连接的IP地址和端口号
   */
  public static SocketChannel createSocketChannel(String ip, int port) {
    SocketChannel socketChannel = null;
    try {
      // 创建SocketChannel
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false); // 设置为非阻塞模式
      // 连接到某台服务器的某个端口
      socketChannel.connect(new InetSocketAddress(ip, port));
      // 判断是否连接完成，若未完成则等待连接
      while (!socketChannel.finishConnect()) {
//        System.out.println("It is connecting>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">");
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // 连接完成返回该SocketChannel
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
        // 阻塞2000ms，判断是否有通道在注册的事件上就绪了，如果有则该返回值就绪通道的个数
        if (selector.select(2000) == 0) {
          System.out.println("please waiting.....");
          continue;
        } else {
          // 当有通道就绪时，获取SelectionKey，并遍历
          Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
          while (keys.hasNext()) {
            SelectionKey key = keys.next();
            // 判断通道中是否可读事件就绪了，如果是则isReadable()方法返回TRUE
            if (key.isReadable()) {
              SocketChannel socketChannel = (SocketChannel) key.channel();
              // 默认服务器端发送的数据都小于1024byte，因此一次可以读完
              ByteBuffer buffer = ByteBuffer.allocate(1024);
              socketChannel.read(buffer); // 利用通道将数据读入buffer中
              buffer.flip(); // 将buffer切换为读模式
              String receiveString =
                  Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
              System.out.println(receiveString);
              buffer.clear(); // 清空缓冲区buffer
            }
            // 设置通道对什么时间感兴趣，该设置是对“读”和“写”感兴趣
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            // 移除当前已经处理过的SelectionKey
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
