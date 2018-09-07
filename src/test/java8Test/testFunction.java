package test.java8Test;

import java.util.function.Function;

public class testFunction {
    public static void main(String[] args) {
        //�򵥵�,ֻ��һ��
        Function<String, String> function1 = (x) -> "test result1: " + x;
 
        //��׼��,�л�����, return, �ֺ�.
        Function<String, String> function2 = (x) -> {
            return "after function1 "+" test result2: " + x;
        };
 
        Function<String, String> function3 = (x) -> {
            return "before function2 "+" test result3: " + x;
        };
        System.out.println(function1.apply("98"));
        System.out.println(function2.compose(function1).apply("100"));
        System.out.println(function1.andThen(function2).apply("100"));//��ִ��function1 Ȼ��������Ϊ�������ݵ�function2��
        System.out.println(function2.andThen(function1).apply("100"));
        System.out.println(function2.compose(function3).apply("fun100"));//��ִ��function3 ��ִ��function2
        System.out.println(Function.identity());
    }
}
 
/**
 test result1: 98
 after function1  test result2: test result1: 100
 after function1  test result2: test result1: 100
 after function1  test result2: before function2  test result3: fun100
 java.util.function.Function$$Lambda$6/558638686@448139f0
 * */
