package test.Enum;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
* 枚举本身的一些方法使用
*/
public class EnumTest {
    public static void main(String[] args) {
        //返回此枚举常量的名称,继承自java.lang.Enum类,通常重写该方法以实现相关操作。输出->SF
        System.out.println(CompanyEnum.SF.toString());
        //返回此枚举常量的名称。输出->SF
        System.out.println(CompanyEnum.SF.name());
        //返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）。输出->0
        System.out.println(CompanyEnum.SF.ordinal());
        //获取枚举中的常量个数。输出->5
        System.out.println(CompanyEnum.SF.values().length);
        //返回带指定名称的指定枚举类型的枚举常量。输出->顺丰速运
        System.out.println(CompanyEnum.SF.valueOf(CompanyEnum.class, "SF").getCompany());  
        //比较此枚举与指定对象的顺序;SF位置是0,YTO位置是1,结果为 0-1=-1。输出->-1
        System.out.println(CompanyEnum.SF.compareTo(CompanyEnum.YTO)); 
        //返回与此枚举常量的枚举类型相对应的 Class 对象。输出->class com.cndmss.util.CompanyEnum
        System.out.println(CompanyEnum.SF.getDeclaringClass());
        //。输出->true
        System.out.println(CompanyEnum.SF.name().equals("SF"));
        //。输出->true
        System.out.println(CompanyEnum.SF.equals(CompanyEnum.SF));
        //。输出->false
        System.out.println(CompanyEnum.SF.equals(CompanyEnum.YTO));
        
        
        
        //1、 EnumSet的使用
       EnumSet<CompanyEnum> companySet = EnumSet.allOf(CompanyEnum.class);
       for (CompanyEnum company:companySet) {
           System.out.println(company.getCode() + ":" + company.getCompany());
       }
       //2、EnumMap的使用
       EnumMap<CompanyEnum, String> companyMap = new EnumMap(CompanyEnum.class);
       companyMap.put(CompanyEnum.SF, "顺丰map");
       companyMap.put(CompanyEnum.YTO, "圆通map");
       Iterator<Entry<CompanyEnum, String>> iter = companyMap.entrySet().iterator();
       while(iter.hasNext()){
           Entry<CompanyEnum, String> entry = iter.next();
           System.out.println(entry.getKey().name() + ":" + entry.getValue());
       }
    }
}