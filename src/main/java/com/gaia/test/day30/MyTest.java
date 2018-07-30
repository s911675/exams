package com.gaia.test.day30;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyTest {
	public static void main(String[] args) {
		MyTest test = new MyTest ();
		
		String value = "asdf af";
		
		test.printDuplicateChars(value);;
	}
	
	public void printDuplicateChars(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		value.chars().map(c ->new String(c)).collect(Collectors.toList());
		
	}
}
