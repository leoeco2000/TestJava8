package test.IO.javaNioStudy.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 客户端
 * 
 * @author sanchan
 */
public class Client {
  public static void main(String[] args) {
    // 需要先启动Server否则报错java.net.ConnectException: Connection refused
    try {
      String msg = "你好,ServerSocket!";
      String msg1 = "1234567890123456789012345";
      // 创建一个Socket,与本机8080端口连接
      Socket socket = new Socket("127.0.0.1", 8080);
      // 使用Socket创建PrintWriter和BufferedReader进行数据的读写
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      out.println(msg1);
      out.flush();
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String line = in.readLine();
      System.out.println(line);
      // 关闭资源
      in.close();
      out.close();
      socket.close();
      System.out
          .println("client关闭" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }
}
