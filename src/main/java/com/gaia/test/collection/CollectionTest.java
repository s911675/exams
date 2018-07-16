package com.gaia.test.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class CollectionTest {
	public static Collection<String> list;
	public static Map<String, Integer> map;
	
	
	static {
		list = new ArrayList<>();
		
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		
		map = new HashMap<>();
		map.put("A", 10);
		map.put("B", 20);
		map.put("C", 30);
		map.put("D", 40);
		map.put("E", 50);
		map.put("F", 60);
	}
	
	public static void main(String[] args) {
		
//		forEachTest ();
		
//		toArrayTest ();
		
		entryTest ();
	}

	private static void entryTest() {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("key");
		}
	}

	private static void toArrayTest() {
		String[] arr = list.toArray(new String[0]);
		
		for(String str : arr) {
			System.out.println(str);
		}
	}

	private static void forEachTest() {
		// TODO Auto-generated method stub
		list.forEach((k) -> {
			System.out.println(k);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
			
		});
	}
}
