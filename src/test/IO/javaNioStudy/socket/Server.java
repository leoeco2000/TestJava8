package test.IO.javaNioStudy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

  public static void main(String[] args) {

    try {
      // 创建一个ServeSocket,设置端口为8080
      ServerSocket serverSocket = new ServerSocket(8080);
      // 运行Socket监听,等待请求 此方法会阻塞线程,当有请求时才会继续执行
      Socket socket = serverSocket.accept();
      // 接收到请求之后使用Socket进行通信,创建BufferedReader用于读取请求的数据
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      String line = in.readLine();
      System.out.println(line);
      // 创建PrintlnWriter,用于发送数据
      out.println("已经接受到了数据");
      out.flush();
      System.out
          .println("Server关闭" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
      // 关闭资源
      out.close();
      in.close();
      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
    }
  }
}
