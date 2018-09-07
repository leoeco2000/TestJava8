package temp;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
  public static void main(String[] args) throws InterruptedException {
    WeakReference<WeakReferenceTest> weakReference =
        new WeakReference<WeakReferenceTest>(new WeakReferenceTest());
    // 第一次打印弱引用所引用的对象
    System.out.println(weakReference.get());
    // 进行一次GC
    System.gc();
    // 由于GC进行需要时间，这里等一秒钟
    Thread.sleep(1000);
    // 再次打印弱引用所引用的对象
    System.out.println(weakReference.get());
  }
}
