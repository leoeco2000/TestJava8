package test.JVM.ClassloadSerial;

public class Parent  
{  
    //��̬��Ա����  
    public static int t = parentStaticMethod2();  
    //�����  
    {  
        System.out.println("����Ǿ�̬��ʼ����");  
    }  
    static  
    {  
        System.out.println("���ྲ̬��ʼ����");  
    }  
    //���췽��  
    public Parent()  
    {  
        System.out.println("����Ĺ��췽��");  
    }  
    //���ྲ̬����  
    public static int parentStaticMethod()  
    {  
        System.out.println("����ľ�̬����");  
        return 10;  
    }  
    public static int parentStaticMethod2()  
    {  
        System.out.println("����ľ�̬����2");  
        return 9;  
    }  
    
    @Override  
    protected void finalize() throws Throwable  
    {  
        // TODO Auto-generated method stub  
        super.finalize();  
        System.out.println("���ٸ���");  
    }  
   
}  