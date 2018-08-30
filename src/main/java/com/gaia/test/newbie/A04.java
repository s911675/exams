package com.gaia.test.newbie;

import java.util.Set;
import java.util.stream.Collectors;

public class A04 {
	public boolean isIdenticalElements(String a, String b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Character> aSet = a.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
		Set<Character> bSet = b.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
		
		return aSet.containsAll(bSet) && bSet.containsAll(aSet);
	}
	
	public static void main(String[] args) {
		String a = "asdf";
		String b = "dadafsd";
		String c = "asdf";
		String d = "dadafsdx";
		
		A04 app = new A04 ();
		
		System.out.println(app.isIdenticalElements(a, b));
		System.out.println(app.isIdenticalElements(c, d));
	}
}
