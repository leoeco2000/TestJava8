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

    // 在打开相应的文件、将输入数据读入名为 inputData 的 ByteBuffer 之后，我们的程序必须创建 ISO-8859-1
    // (Latin1) 字符集的一个实例：
    Charset latin1 = Charset.forName("ISO-8859-1");

    // 创建一个解码器（用于读取）和一个编码器 （用于写入）：
    CharsetDecoder decoder = latin1.newDecoder();
    CharsetEncoder encoder = latin1.newEncoder();

    // 为了将字节数据解码为一组字符，我们把 ByteBuffer 传递给 CharsetDecoder，结果得到一个 CharBuffer：
    CharBuffer cb = decoder.decode(inputData);

    // 如果想要处理字符，我们可以在程序的此处进行。但是我们只想无改变地将它写回，所以没有什么要做的。
    // 要写回数据，我们必须使用 CharsetEncoder 将它转换回字节：
    ByteBuffer outputData = encoder.encode(cb);

    outc.write(outputData);

    inf.close();
    outf.close();
  }
}
