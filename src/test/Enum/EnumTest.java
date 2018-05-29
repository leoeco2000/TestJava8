package test.Enum;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
* ö�ٱ����һЩ����ʹ��
*/
public class EnumTest {
    public static void main(String[] args) {
        //���ش�ö�ٳ���������,�̳���java.lang.Enum��,ͨ����д�÷�����ʵ����ز��������->SF
        System.out.println(CompanyEnum.SF.toString());
        //���ش�ö�ٳ��������ơ����->SF
        System.out.println(CompanyEnum.SF.name());
        //����ö�ٳ���������������ö�������е�λ�ã����г�ʼ��������Ϊ�㣩�����->0
        System.out.println(CompanyEnum.SF.ordinal());
        //��ȡö���еĳ������������->5
        System.out.println(CompanyEnum.SF.values().length);
        //���ش�ָ�����Ƶ�ָ��ö�����͵�ö�ٳ��������->˳������
        System.out.println(CompanyEnum.SF.valueOf(CompanyEnum.class, "SF").getCompany());  
        //�Ƚϴ�ö����ָ�������˳��;SFλ����0,YTOλ����1,���Ϊ 0-1=-1�����->-1
        System.out.println(CompanyEnum.SF.compareTo(CompanyEnum.YTO)); 
        //�������ö�ٳ�����ö���������Ӧ�� Class �������->class com.cndmss.util.CompanyEnum
        System.out.println(CompanyEnum.SF.getDeclaringClass());
        //�����->true
        System.out.println(CompanyEnum.SF.name().equals("SF"));
        //�����->true
        System.out.println(CompanyEnum.SF.equals(CompanyEnum.SF));
        //�����->false
        System.out.println(CompanyEnum.SF.equals(CompanyEnum.YTO));
        
        
        
        //1�� EnumSet��ʹ��
       EnumSet<CompanyEnum> companySet = EnumSet.allOf(CompanyEnum.class);
       for (CompanyEnum company:companySet) {
           System.out.println(company.getCode() + ":" + company.getCompany());
       }
       //2��EnumMap��ʹ��
       EnumMap<CompanyEnum, String> companyMap = new EnumMap(CompanyEnum.class);
       companyMap.put(CompanyEnum.SF, "˳��map");
       companyMap.put(CompanyEnum.YTO, "Բͨmap");
       Iterator<Entry<CompanyEnum, String>> iter = companyMap.entrySet().iterator();
       while(iter.hasNext()){
           Entry<CompanyEnum, String> entry = iter.next();
           System.out.println(entry.getKey().name() + ":" + entry.getValue());
       }
    }
}