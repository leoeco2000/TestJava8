package laobing.concurrency.chapter1_thread.superTest;

class Extends_Demo {
    public static void main(String[] args) {
        Cat c = new Cat();            //---------------(1)
        System.out.println("-------------------");
        Cat c1 = new Cat("����",4);   //----------------(2)
    }
}
class Animal {
    private String color;
    private int foot;

    public Animal(){
        System.out.println("���Ǹ����޲���������");
    }

    public Animal(String color,int foot){
        System.out.println("���Ǹ����в���������");
        this.color = color;
        this.foot  = foot;
    }
}
class Cat extends Animal{

    public Cat(){
        super();                     //---------------����ʡ��
        System.out.println("���������޲���������");
    }

    public Cat(String color,int foot){              
        super(color,foot);         //---------------(3)��ʽ���ø����вι��캯��
//        super();                     //---------------����ʡ��
        System.out.println("���������в���������");
    }
}