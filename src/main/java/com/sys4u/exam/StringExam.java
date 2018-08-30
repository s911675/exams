package com.sys4u.exam;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExam {
	public static void main(String[] args) {
		StringExam app = new StringExam();

		String value = "asdfaf";

		Exception ex = new Exception ();
		ex.printStackTrace();
		
		app.printDuplicatedChars(value);

		System.out.println(app.isPalindrome(value));
		System.out.println(app.isPalindrome("1234321"));

		System.out.println(app.removeDuplicated(value));
		System.out.println(app.removeDuplicated("1234321"));

		System.out.println(app.isIdenticalElements(value, value));
		System.out.println(app.isIdenticalElements(value, "1234321"));
	}

	public void printDuplicatedChars(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		value.chars().mapToObj(c -> Character.valueOf((char)c))
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
	}
	
	public boolean isPalindrome(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		return new StringBuilder (value).reverse().toString().equals(value);
	}
	
	public String removeDuplicated(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		return value.chars().mapToObj(c -> String.valueOf((char)c)).distinct()
		.collect(Collectors.joining());
	}
	
	public boolean isIdenticalElements(String a, String b) {
		if(a==null || b==null) {
			throw new IllegalArgumentException();
		}
		
		Set<Character> aSet = a.chars().mapToObj(c -> Character.valueOf((char)c)).collect(Collectors.toSet());
		Set<Character> bSet = b.chars().mapToObj(c -> Character.valueOf((char)c)).collect(Collectors.toSet());
		
		return aSet.containsAll(bSet) && bSet.containsAll(aSet);
	}
}
