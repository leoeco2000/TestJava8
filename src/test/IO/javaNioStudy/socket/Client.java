package test.IO.javaNioStudy.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * �ͻ���
 * 
 * @author sanchan
 */
public class Client {
  public static void main(String[] args) {
    // ��Ҫ������Server���򱨴�java.net.ConnectException: Connection refused
    try {
      String msg = "���,ServerSocket!";
      String msg1 = "1234567890123456789012345";
      // ����һ��Socket,�뱾��8080�˿�����
      Socket socket = new Socket("127.0.0.1", 8080);
      // ʹ��Socket����PrintWriter��BufferedReader�������ݵĶ�д
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      out.println(msg1);
      out.flush();
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String line = in.readLine();
      System.out.println(line);
      // �ر���Դ
      in.close();
      out.close();
      socket.close();
      System.out
          .println("client�ر�" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }
}
