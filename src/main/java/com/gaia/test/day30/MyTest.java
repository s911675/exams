package com.gaia.test.day30;

import java.util.function.Function;
import java.util.stream.Collectors;

public class MyTest {
	public static void main(String[] args) {
		MyTest test = new MyTest ();
		
		String value = "as df af";
		
		test.printDuplicateChars(value);;
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
}
