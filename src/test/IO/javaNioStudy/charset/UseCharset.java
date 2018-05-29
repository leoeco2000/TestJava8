package test.IO.javaNioStudy.charset;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class UseCharset {
  public static void main(String[] args) throws Exception {
//    String inputFile = UseCharset.class.getClassLoader().getResource("a.txt").getFile();
//    String outFile = UseCharset.class.getClassLoader().getResource("b.txt").getFile();

    String inputFile = "data/copy.txt";
    String outFile = "data/paste.txt";
    
    RandomAccessFile inf = new RandomAccessFile(inputFile, "r");
    RandomAccessFile outf = new RandomAccessFile(outFile, "rw");

    long length = new File(inputFile).length();

    FileChannel inc = inf.getChannel();
    FileChannel outc = outf.getChannel();

    MappedByteBuffer inputData = inc.map(FileChannel.MapMode.READ_ONLY, 0, length);

    // �ڴ���Ӧ���ļ������������ݶ�����Ϊ inputData �� ByteBuffer ֮�����ǵĳ�����봴�� ISO-8859-1
    // (Latin1) �ַ�����һ��ʵ����
    Charset latin1 = Charset.forName("ISO-8859-1");

    // ����һ�������������ڶ�ȡ����һ�������� ������д�룩��
    CharsetDecoder decoder = latin1.newDecoder();
    CharsetEncoder encoder = latin1.newEncoder();

    // Ϊ�˽��ֽ����ݽ���Ϊһ���ַ������ǰ� ByteBuffer ���ݸ� CharsetDecoder������õ�һ�� CharBuffer��
    CharBuffer cb = decoder.decode(inputData);

    // �����Ҫ�����ַ������ǿ����ڳ���Ĵ˴����С���������ֻ���޸ı�ؽ���д�أ�����û��ʲôҪ���ġ�
    // Ҫд�����ݣ����Ǳ���ʹ�� CharsetEncoder ����ת�����ֽڣ�
    ByteBuffer outputData = encoder.encode(cb);

    outc.write(outputData);

    inf.close();
    outf.close();
  }
}
