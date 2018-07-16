package com.gaia.test.stream.test;

import java.util.Set;
import java.util.stream.Collectors;

public class IdenticalElement {
	public boolean isIdenticalElements(String a, String b) {
		if(a == null || b == null) {
			throw new IllegalArgumentException();
		}
		
		Set<String> setA = a.chars().mapToObj(x -> String.valueOf((char)x)).collect(Collectors.toSet());
		Set<String> setB = b.chars().mapToObj(x -> String.valueOf((char)x)).collect(Collectors.toSet());
		
		return setB.containsAll(setA);
	}
	
	
	public static void main(String[] args) {
		IdenticalElement test = new IdenticalElement();
		
		System.out.println(test.isIdenticalElements("abcd", "edbca"));
		System.out.println(test.isIdenticalElements("abcde", "abc1e"));
	}

}
