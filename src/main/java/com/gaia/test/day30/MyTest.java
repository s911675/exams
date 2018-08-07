package com.gaia.test.day30;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyTest {
	public static void main(String[] args) {
		MyTest test = new MyTest ();
		
		String value1 = "as df af";
		String value2 = "as df afx";
		
		test.printDuplicateChars(value1);
		System.out.println(test.isPalindrome(value1));
		System.out.println(test.removeDuplicated(value1));
		System.out.println(test.isIdenticalElements(value1, value1));
		System.out.println(test.isIdenticalElements(value1, value2));
	}
	
	public void printDuplicateChars(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		value.chars()
		.mapToObj(c -> (char)c)
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream().filter(x -> x.getValue() > 1)
		.forEach(x -> System.out.println(String.format("%c:%d", x.getKey(), x.getValue())));
	}
	
	public boolean isPalindrome(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		String reverseValue = new StringBuffer(value).reverse().toString();
		
		return value.equals(reverseValue) ? true : false;
	}
	
	public String removeDuplicated(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		return value.chars().mapToObj(c -> String.valueOf((char)c)).distinct().collect(Collectors.joining());
	}
	
	public boolean isIdenticalElements(String a, String b) {
		if(a == null || b == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Character> aSet = a.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
		Set<Character> bSet = b.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
		
		return aSet.containsAll(bSet) && bSet.containsAll(aSet) ? true : false;
	}
	
	/*
	SELECT B.uid, B.title, B.contents, BU.name, B.created)date
		C.uid AS do_uid, C.comment, CU.name, C.created_date
	FROM board B, comment C, customer BU, customer CU
	WHER B.uid = C.board_uid
		AND B.created_by = BU.login_id
		AND C.created_by = CU.login_id
	 */
}
