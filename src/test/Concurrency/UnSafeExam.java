package test.Concurrency;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UnSafeExam {
//    public static void main(String[] args) throws InstantiationException, NoSuchFieldException {
//        //���һ��UnSafeʵ��
//        Unsafe unsafe = null;
//        try {
//            Field f = Unsafe.class.getDeclaredField("theUnsafe");
//            f.setAccessible(true);
//            unsafe = (Unsafe) f.get(null);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        if (unsafe != null) {
//            try {
//                //����һ�������Ҳ������乹�캯��
//                Test test = (Test) unsafe.allocateInstance(Test.class);
//                //�õ�һ�������ڲ����Եĵ�ַ
//                long x_addr = unsafe.objectFieldOffset(Test.class.getDeclaredField("x"));
//                //ֱ�Ӹ������Ը�ֵ
//                unsafe.getAndSetInt(test, x_addr, 47);
//                System.out.println(test.getX());
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //ͨ����ַ��������
//        if (unsafe != null) {
//            final int INT_BYTES = 4;
//            int[] data = new int[10];
//            System.out.println(Arrays.toString(data));
//            long arrayBaseOffset = unsafe.arrayBaseOffset(int[].class);
//            System.out.println("Array address is :" + arrayBaseOffset);
//            unsafe.putInt(data, arrayBaseOffset, 47);
//            unsafe.putInt(data, arrayBaseOffset + INT_BYTES * 8, 43);
//            System.out.println(Arrays.toString(data));
//        }
//
//        //CAS
//        if (unsafe != null) {
//            Test test = (Test) unsafe.allocateInstance(Test.class);
//            long x_addr = unsafe.objectFieldOffset(Test.class.getDeclaredField("x"));
//            unsafe.getAndSetInt(test, x_addr, 47);
//            unsafe.compareAndSwapInt(test, x_addr, 47, 78);
//            System.out.println("After CAS:" + test.getX());
//        }
//
//    }
//
//    static class Test {
//        private final int x;
//
//        Test(int x) {
//            this.x = x;
//            System.out.println("Test ctor");
//        }
//
//        int getX() {
//            return x;
//        }
//
//    }
}