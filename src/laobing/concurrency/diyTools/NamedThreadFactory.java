package laobing.concurrency.diyTools;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
  private final AtomicInteger threadNumberAtomicInteger = new AtomicInteger(1);
  @Override
  public Thread newThread(Runnable r) {
      Thread thread=  new Thread(r,String.format(Locale.CHINA,"%s%d","NamedThreadFactory",threadNumberAtomicInteger.getAndIncrement()));
      /* thread.setDaemon(true);//�Ƿ����ػ��߳�
      thread.setPriority(Thread.NORM_PRIORITY);//�������ȼ� 1~10 ��3������ Ĭ�� Thread.MIN_PRIORITY*/
      return thread;
  }
}
