package IO.javaNioStudy.bufferTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
  public static void main(String[] args) throws IOException {
    // �õ��ļ�����·��
    String filePath = "data/d";
    File file = new File(filePath);
    FileInputStream ins = new FileInputStream(file);
    // �õ�ͨ�� FileChannel
    FileChannel fIns = ins.getChannel();
    /*
     * �ڴ�ӳ���ļ���ӳ��Ĵ�С�Ǵ�0����file.length()��ô��
     */
    MappedByteBuffer mbb = fIns.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
    // �����ݵ� data��
    byte[] data = new byte[(int) file.length()];
    int index = 0;
    while (mbb.hasRemaining()) {
      data[index] = mbb.get();
      index++;
    }
    // ���
    System.out.println(new String(data));
    // close
    fIns.close();
    ins.close();
  }
}
