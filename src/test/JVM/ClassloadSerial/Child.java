package test.JVM.ClassloadSerial;

public class Child extends Parent  
{  
    //�����  
    {  
        System.out.println("����Ǿ�̬��ʼ����");  
    }  
    static  
    {  
        System.out.println("���ྲ̬��ʼ����");  
    }  
    //���췽��  
    public Child()  
    {  
        System.out.println("����Ĺ��췽��");  
    }  
    //��̬����  
    public static int childStaticMethod()  
    {  
        System.out.println("����ľ�̬����");  
        return 1000;  
    }  
      
    @Override  
    protected void finalize() throws Throwable  
    {  
        // TODO Auto-generated method stub  
        super.finalize();  
        System.out.println("��������");  
    }  
}  