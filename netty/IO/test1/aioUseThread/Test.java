package IO.test1.aioUseThread;

import java.util.Scanner;
import IO.test1.aioUseThread.client.Client;
import IO.test1.aioUseThread.server.Server;

/**
 * ���Է���
 * 
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class Test {
  // ����������
  @SuppressWarnings("resource")
  public static void main(String[] args) throws Exception {
    // ���з�����
    Server.start();
    // ����ͻ������ڷ���������ǰִ�д���
    Thread.sleep(100);
    // ���пͻ���
    Client.start();
    System.out.println("������������Ϣ��");
    Scanner scanner = new Scanner(System.in);
    while (Client.sendMsg(scanner.nextLine()));
  }
}
