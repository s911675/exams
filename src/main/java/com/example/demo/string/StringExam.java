package com.example.demo.string;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringExam {
	public static void main(String[] args) {
		StringExam stringTest = new StringExam();
		String value = "asd fs d";

		stringTest.printDuplicatedChars(value);
		/*
		value =  "asdffdsa";
		System.out.println("isPalindrome(" + value + ") = "  + stringTest.isPalindrome(value));
		value =  "as41";
		System.out.println("isPalindrome(" + value + ") = "  + stringTest.isPalindrome("as41"));

		value =  "as1241";
		System.out.println("removeDuplicated(" + value + ") = "  + stringTest.removeDuplicated("as1241"));
		*/
		String a =  "as1241";
		String b = "1241as";
		
		System.out.println("isIdenticalElements(" + a + "," + b + ") = "  + stringTest.isIdenticalElements(a, b));
		b = "asdf123";
		System.out.println("isIdenticalElements(" + a + "," + b + ") = "  + stringTest.isIdenticalElements(a, b));

		a = "";
		b = "";
		System.out.println("isIdenticalElements(" + a + "," + b + ") = "  + stringTest.isIdenticalElements(a, b));
	}

	public void printDuplicatedChars(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		value.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(x -> x.getValue() > 1)
				.forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));
	}
	
	public boolean isPalindrome(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		String reverseValue = new StringBuilder(value).reverse().toString();
		
		return value.equals(reverseValue) ? true : false;
	}
	
	public String removeDuplicated(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		return value.chars().mapToObj(c -> String.valueOf((char)c)).distinct().collect(Collectors.joining());
	}
	
	public boolean isIdenticalElements(String a, String b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Character> aSet = a.chars().mapToObj(c -> (char)c).distinct().collect(Collectors.toSet());
		Set<Character> bSet = b.chars().mapToObj(c -> (char)c).distinct().collect(Collectors.toSet());
		
		return aSet.containsAll(bSet) && bSet.containsAll(aSet) ? true : false;
	}
}
