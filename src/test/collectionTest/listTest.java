package test.collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class listTest {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("a");
		l.add("a");
		System.out.println(l.get(0).equals(l.get(1)));
		String[] arr = l.toArray(new String[0]);
		l = Arrays.asList(arr);
		for(int i=0;i<arr.length;i++) {
			System.out.println(l.get(i));
		}
		
		
		List<String> ld = new LinkedList<String>();
		ld.add("a");
		
		
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(1, "c");
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry e = (Entry) it.next();
			e.getKey();
			
		}
		for(Entry<Integer, String> e: map.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
	}
}
