package com.gaia.test.stream.test;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicated {
	public String removeDuplicated(String value) {
		if( value == null) {
			throw new IllegalArgumentException();
		}
		
		List<String> list = value.chars().mapToObj(x -> String.valueOf((char)x)).collect(Collectors.toList());
		
		return list.stream().distinct().collect(Collectors.joining());
	}
	
	public static void main(String[] args) {
		RemoveDuplicated test = new RemoveDuplicated ();
		
		String value1 = "a1sdf";
		String value2 = "asdfasd";
		
		System.out.println(test.removeDuplicated(value1));
		System.out.println(test.removeDuplicated(value2));
	}
}
