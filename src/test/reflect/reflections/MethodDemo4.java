package test.reflect.reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodDemo4 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();

		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("hello");
		//list1.add(20);éè¯¯ç?
		Class c1 = list.getClass();
		Class c2 = list1.getClass();
		System.out.println(c1 == c2);
		//åå°çæä½é½æ¯ç¼è¯ä¹åçæä½

		/*
		 * c1==c2ç»æè¿åtrueè¯´æç¼è¯ä¹åéåçæ³åæ¯å»æ³ååç?
		 * Javaä¸­éåçæ³åï¼æ¯é²æ­¢éè¯¯è¾å¥çï¼åªå¨ç¼è¯é¶æ®µææï¼?
		 * ç»è¿ç¼è¯å°±æ æäº
		 * éªè¯ï¼æä»¬å¯ä»¥é?è¿æ¹æ³çåå°æ¥æä½ï¼ç»è¿ç¼è¯?
		 */
		try {
			Method m = c2.getMethod("add", Object.class);
			m.invoke(list1, 20);//ç»è¿ç¼è¯æä½å°±ç»è¿äºæ³å
			System.out.println(list1.size());
			System.out.println(list1);
			/*for (String string : list1) {
				System.out.println(string);
			}*///ç°å¨ä¸è½è¿æ ·éå
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
