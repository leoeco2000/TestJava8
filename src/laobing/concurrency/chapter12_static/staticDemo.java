package laobing.concurrency.chapter12_static;

public class staticDemo {
//  ʲôʱ����ܻ�õ���ȷ�Ľ����
//
//  ��ʹ�ù����ڴ棬ÿ���߳��ڴ�ռ��໥������
//  ���̹߳���һ���ڴ����򣬵��Ƕ���鹲������������ʡ��Ե���static�����ķ���ʹ��lock��synchronized
    static int result;
    static int Addone(Integer num){
        Integer inner_result=num++;
        return inner_result;
    }
    public static void main(String[] args) {

    }
}