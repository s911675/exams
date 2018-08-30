package com.gaia.test.newbie;

import java.util.function.Function;
import java.util.stream.Collectors;

public class A01 {
	public void printDuplicatedChars(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		value.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream().filter(x -> x.getValue() > 1)
		.forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));
	}

	public static void main(String[] args) {
		String value = "asdfsf";

		A01 app = new A01();
		
		app.printDuplicatedChars(value);
	}
}
