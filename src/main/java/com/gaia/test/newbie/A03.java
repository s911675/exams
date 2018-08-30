package com.gaia.test.newbie;

import java.util.stream.Collectors;

public class A03 {
	public String removeDuplicated(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		
		return value.chars()
				.mapToObj(c -> String.valueOf((char)c))
				.distinct()
				.collect(Collectors.joining());
	}
	
	public static void main(String[] args) {
		String value = "asdfsf";
		String value2 = "12321";
		
		A03 app = new A03 ();
		System.out.println(app.removeDuplicated(value));
		System.out.println(app.removeDuplicated(value2));
	}
}
