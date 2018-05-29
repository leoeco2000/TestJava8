package test.java.clone;

import java.util.ArrayList;

class Prototype implements Cloneable {
    private ArrayList list = new ArrayList();
    public Prototype clone(){
        Prototype prototype = null;
        try{
            prototype = (Prototype)super.clone();
            prototype.list = (ArrayList) this.list.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return prototype;
    }
}

class ConcretePrototype extends Prototype{
    public void show(){
        System.out.println("原型模式实现类");
    }
}

public class testPrototype {
    public static void main(String[] args){
        ConcretePrototype cp = new ConcretePrototype();
        ConcretePrototype clonecp = null;
		clonecp = (ConcretePrototype)cp.clone();
        clonecp.show();
    }
}