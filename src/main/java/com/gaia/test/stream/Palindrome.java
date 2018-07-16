package com.gaia.test.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Palindrome {

	public static Boolean isPalindromeA (String value) {
		
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		Boolean b = Boolean.TRUE;

		for(int i=0,k=value.length()/2;i<k;i++) {
			if(value.charAt(i) != value.charAt((value.length()-1)-i)) {
				b = Boolean.FALSE;
				break;
			}
		}
		
		
		return b;
	}

	public static Boolean isPalindromeB (String value) {
		
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		Boolean b = Boolean.TRUE;

		List<String> list = value.chars().mapToObj(x -> String.valueOf((char) x)).collect(Collectors.toList());
		
		String reverseValue = list.stream()
	    .sorted(Comparator.reverseOrder()).collect(Collectors.joining());
		
		if(!value.equals(reverseValue)) {
			b = Boolean.FALSE;
		}
		
		return b;
	}
	
	public static Boolean isPalindromeC (String value) {
		
		if(value == null) {
			throw new IllegalArgumentException();
		}

		return value.equals(new StringBuffer(value).reverse().toString());
	}
	
	public static void main(String[] args) {
		String a = "asdf";
		String b = "asa";
		String c = "assa";
		
		System.out.println(isPalindromeC(a));
		System.out.println(isPalindromeC(b));
		System.out.println(isPalindromeC(c));
	}

}
