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
      // ����һ��ServeSocket,���ö˿�Ϊ8080
      ServerSocket serverSocket = new ServerSocket(8080);
      // ����Socket����,�ȴ����� �˷����������߳�,��������ʱ�Ż����ִ��
      Socket socket = serverSocket.accept();
      // ���յ�����֮��ʹ��Socket����ͨ��,����BufferedReader���ڶ�ȡ���������
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      String line = in.readLine();
      System.out.println(line);
      // ����PrintlnWriter,���ڷ�������
      out.println("�Ѿ����ܵ�������");
      out.flush();
      System.out
          .println("Server�ر�" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
      // �ر���Դ
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
