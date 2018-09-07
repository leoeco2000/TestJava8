package temp;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
  public static void main(String[] args) throws InterruptedException {
    WeakReference<WeakReferenceTest> weakReference =
        new WeakReference<WeakReferenceTest>(new WeakReferenceTest());
    // ��һ�δ�ӡ�����������õĶ���
    System.out.println(weakReference.get());
    // ����һ��GC
    System.gc();
    // ����GC������Ҫʱ�䣬�����һ����
    Thread.sleep(1000);
    // �ٴδ�ӡ�����������õĶ���
    System.out.println(weakReference.get());
  }
}
