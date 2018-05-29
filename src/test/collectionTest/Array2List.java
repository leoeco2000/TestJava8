package test.collectionTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Array2List {
	public static void main(String[] args) {
//		ArrayDeque deq1 = new ArrayDeque();
//		deq1.push(1);
//		deq1.push(2);
//		deq1.pop();
//		deq1.add(3);
//		deq1.remove(3);
//		
//		LinkedList l1 = new LinkedList();
//		l1.add(0, 1);
//		l1.add(0, 3);
//		l1.set(0, 2);
//
//		System.out.println(l1.size());
		
		
	    String[] array1 = new String[] {"zhu", "wen", "tao"};
	    // String数组转List集合
	    List<String> mlist1 = Arrays.asList(array1);
	    // 输出List集合
	    for (int i = 0; i < mlist1.size(); i++) {
	        System.out.println("mlist-->" + mlist1.get(i));
	    }
		
	    List<String> mlist = new ArrayList<String>();
	    mlist.add("zhu");
	    mlist.add("wen");
	    mlist.add("tao");
	    // List转成数组
	    String[] array = mlist.toArray(new String[0]);
	    // 输出数组
	    for (int i = 0; i < array.length; i++) {
	        System.out.println("array--> " + array[i]);
	    }

	    ArrayList<String> arr = new ArrayList<>();
	    long start =System.currentTimeMillis();
	    for (int i = 0; i < 500000; i++) {
//	        arr.add("a");
	        arr.add((int) Math.ceil(arr.size()/2), "a");
	    }
//	    for (int i = 0; i < 100000; i++) {
//	        arr.remove(Math.ceil(arr.size()/2));
//	    }
	    long end=System.currentTimeMillis();
	    System.out.println("arrylist time:" + (end - start));


	    LinkedList<String> link = new LinkedList<>();
	    start =System.currentTimeMillis();
	    for (int i = 0; i < 500000; i++) {
//	        arr.add("a");
	        arr.add((int) Math.ceil(arr.size()/2), "a");
	    }
//	    for (int i = 0; i < 100000; i++) {
//	        arr.remove(Math.ceil(arr.size()/2));
//	    }
	    end=System.currentTimeMillis();
	    System.out.println("linkedlist time:" + (end - start));

	}
}

