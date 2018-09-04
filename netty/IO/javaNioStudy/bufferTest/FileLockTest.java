package IO.javaNioStudy.bufferTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;
 
/**
 * FileLocke���ļ����������������Ʋ�ͬ����JVM����ͬһ�ļ��Ĳ�������
 * FileLock��java 1.4 �汾����ֵ�һ���࣬������ͨ����һ����д�ļ�(w)������
 * ��֤ͬʱֻ��һ�����̿����õ��ļ�������������̴Ӷ����Զ��ļ������ʣ�
 * �������ò������Ľ���Ҫôѡ�񱻹���ȴ���Ҫôѡ��ȥ��һЩ���������飬
 * �����Ļ��Ʊ�֤���ڽ��̿���˳����ʸ��ļ���
 * Ҳ���Կ������ܹ������ļ������������ʣ���һЩ�����£���Ȼ���ǲ���Ҫ����ĳ���ļ���
 * ��Ҳ����ͨ�� FileLock �����в������ƣ���֤���̵�˳��ִ�У��������ݴ���
 * ---���ڽ��̼䲢��
 * Locks are associated with files, not channels. Use locks to coordinate 
 * with external processes, not between threads in the same JVM.
 * ---����
 * ������:�������������ֻ��һ��д
 * ������:ֻ��һ������һ��д
 * API��˵���ļ����Ƕ�ռ���߹���ģ���������ֹ�����������еĳ������ظ��Ķ�ռ���������������ǻ��
 * �ظ��Ĺ���������ռ����ֹ�����������κ����͵�����һ�����ͷţ����Ͷ������������Ҫ��õ���û���˺���Ӱ��.
 * ---FileLock FileChannel.lock(long position, long size, boolean shared),�ļ���
 * shared�ĺ���:�Ƿ�ʹ�ù�����,һЩ��֧�ֹ������Ĳ���ϵͳ,���Զ����������ĳ�������.
 * ����ͨ������isShared()����������õ���ʲô���͵���
 * ---lock()��tryLock()������
 * lock()�����ķ�����������Χ���������ļ������������
 * tryLock()������,��δ�����ʱ,����null.
 * ---FileLock����������
 * �ڵ���FileLock.release(),����Channel.close(),����JVM�ر�
 * ---FileLock���̰߳�ȫ��
 * ---boolean java.nio.channels.FileLock.overlaps(long position, long size)
 * true��ʾ��ǰ����������,false��ʾ��ǰ������������������ص�
 *  ע�����
  1.ͬһ�����ڣ����ļ���û�б��ͷ�֮ǰ���������ٴλ�ȡ��
       ����release()��������ǰ,ֻ��lock()����tryLock()һ�Ρ�
  2.�ļ�����Ч���������ϵͳ��صġ�һЩϵͳ���ļ�����ǿ���Եģ�mandatory����
       �͵�Java��ĳ���̻���ļ����󣬲���ϵͳ����֤���������޷����ļ��������ˡ�
       ����һЩ����ϵͳ���ļ�����ѯ��ʽ��(advisory)����˼��˵Ҫ��ӵ�н��̻����Ч����
       �����Ľ���Ҳ����Ҳ����API���涨��������������߼���ļ�������Ȼ�����𲻵����̻���Ĺ��ܡ�
       ���ԣ��ĵ��ｨ�齫����ϵͳ��������ѯ��ʽϵͳ����������������Ӱ�ȫҲ��������ֲ��
  *��α�������
  �ڶ�д�ؼ�����ʱ������������ɺ������ 
  һ��������������Ҫ����Դ�����������벻�ɹ�������·��������뵽����Դ;
 * @author shijin
 *
 */
public class FileLockTest {
  /**
   * �������ʾ����Ҫ���л���Ľ���ֻҪ���Լ��Ĵ����滻��//����������ɣ�
   * ÿ������������ʵ���߼����ܴ���֮ǰ���᳢�Ի�ȡ���ļ�����
   * �õ��ļ����Ľ��̿��Լ���ִ�к����Ĵ��룬��û�л�����ļ��Ľ��̽�������ϵͳ����(suspend)��
   * �ȵ��������̽��ļ����ͷź������¿�ʼ���Ի�ȡ�ļ�����
   * �����ӣ����̾Ϳ���ͨ��FileLock��ʵ�ּ�Ļ������С�
   * @param args
   */
  public static void main(String[] args){
    FileChannel channel = null;
    FileLock lock = null;
    try {
//      ����һ��ֻ���ļ�ͨ�����ⷽʽ����ʱ�ᱨNonWritableChannelException�쳣
//      ͬ����дͨ��ͨ���в�lock()��ʽ����ʱҲ�ᱨNonReadableChannelException�쳣
//      �޲�lock()Ĭ��Ϊ��ռ�������ᱨNonReadableChannelException�쳣����Ϊ��ռ����Ϊ��д
//      ��ν�Ĺ���Ҳֻ�ܶ�����д�Ƕ�ռ�ģ����������ƵĴ���ֻ���Ƕ�����
//      channel = new FileOutputStream("logfile.txt",true).getChannel();
      
      RandomAccessFile raf = new RandomAccessFile("logfile.txt","rw");
      raf.seek(raf.length());//raf���ļ�ĩβ׷�����ݵĴ���
      channel = raf.getChannel();
      
//      ���������һlock�������ķ��������ļ���������ʱ����ǰ���̻ᱻ����
//      lock = channel.lock(0L, Long.MAX_VALUE, true);//����������д�����ᱨ�쳣
      lock = channel.lock();//��ռ��
      
//      �����������trylock���������ķ��������ļ���������ʱ��tryLock()��õ�nullֵ
//      do {
//        lock = channel.tryLock();
//      } while(null == lock);
 
//      �������
      ByteBuffer sendBuffer=ByteBuffer.wrap((new Date()+" д��\n").getBytes());
      channel.write(sendBuffer);
      Thread.sleep(5000);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      if(lock != null) {
        try {
          lock.release();
          lock = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(channel != null) {
        try {
          channel.close();
          channel = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
/**
 *  ���һ�����������б����򣬳�������ļ����Ŀ����¶�logfile.txt���л������
  logfile.txt���ݣ�
  Thu Aug 16 15:39:02 CST 2012 д��
  Thu Aug 16 15:39:07 CST 2012 д��
  
  �����õڶ��ַ���ʱ������δ����ļ����Ͷ��ļ����в�������ᱨ�����쳣��
  Exception in thread "main" java.io.IOException: ��һ�������������ļ���һ���֣������޷����ʡ�
  at sun.nio.ch.FileDispatcher.write0(Native Method)
  at sun.nio.ch.FileDispatcher.write(Unknown Source)
  at sun.nio.ch.IOUtil.writeFromNativeBuffer(Unknown Source)
  at sun.nio.ch.IOUtil.write(Unknown Source)
  at sun.nio.ch.FileChannelImpl.write(Unknown Source)
  at FileLockTest.main(FileLockTest.java:19)
 */
