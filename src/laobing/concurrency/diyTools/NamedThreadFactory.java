package laobing.concurrency.diyTools;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
  private final AtomicInteger threadNumberAtomicInteger = new AtomicInteger(1);
  @Override
  public Thread newThread(Runnable r) {
      Thread thread=  new Thread(r,String.format(Locale.CHINA,"%s%d","NamedThreadFactory",threadNumberAtomicInteger.getAndIncrement()));
      /* thread.setDaemon(true);//是否是守护线程
      thread.setPriority(Thread.NORM_PRIORITY);//设置优先级 1~10 有3个常量 默认 Thread.MIN_PRIORITY*/
      return thread;
  }
}
