package test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.HashCodeBuilder;

class testOverrideEquals{
	public static void main(String... args) {
	    // Person类的实例作为Map的key 
	    Map<Person, Object> map = new HashMap<Person, Object>();
	    map.put(new Person("张三"), new Object()); 
	        
	    // Person类的实例作为List的元素 
	    List<Person> list = new ArrayList<Person>();
	    list.add(new Person("张三")); 
	 
	    // 列表中是否包含 
	    boolean b1 = list.contains(new Person("张三")); 
	    // Map中是否包含 
	    boolean b2 = map.containsKey(new Person("张三"));
	    System.out.println(b1 + "|" + b2);
	}

}

class Person {
    private String name;
    private String age;
   
    public Person(String name) {
        setName(name);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
   
    @Override
    public boolean equals(Object o) {
        /*
         * 使用getClass是为了防止子类被判断为父类的情况
         * 例如：Person中覆写equals方法 {return name.equals(p.getName);}
         *     子类Employee继承Person,此时子类中的euqals方法super.equlas(p);
         * 此时如果使用instanceof关键字，因为Employee是Person的子类，所以返回true
         *  进一步调用equals返回的是true，所以会将Person和Employee认为是同一人，显然不合理
         */
        if(o != null && o.getClass() == this.getClass()) {
            Person p = (Person) o;
            if(p.getName() == null && this.name == null)
                return false;
            return this.name.equals(p.getName());
        }
        return false;
    }

    @Override
	public int hashCode() {
	     //其中HashCodeBuilder是org.apache.commons.lang.builder包下的一个哈希码生成工具，
	     //使用起来非常方便，诸位可以直接在项目中集成。
	     return new HashCodeBuilder().append(name).toHashCode();
	}


}