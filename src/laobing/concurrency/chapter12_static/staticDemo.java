package laobing.concurrency.chapter12_static;

public class staticDemo {
//  什么时候可能会得到正确的结果：
//
//  不使用共享内存，每个线程内存空间相互独立；
//  多线程共享一块内存区域，但是对这块共享区域加锁访问。对调用static变量的方法使用lock或synchronized
    static int result;
    static int Addone(Integer num){
        Integer inner_result=num++;
        return inner_result;
    }
    public static void main(String[] args) {

    }
}