package com.example.demo;

import java.util.Date;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JavaMain {
	public static final int NUMBER1 = 111;
	
	enum Something {
		INSTANCE;
		public final Boolean NUMBER2 = Boolean.valueOf(true);
		
		public String getName() {
			return "Something";
		}
		
		public void printNumbers() {
			System.out.println("Number1: " + NUMBER1 +
					"\nNumber2: " + NUMBER2);
		}
	}
	
	public JavaMain() {
		
	}
	
	public static void main(String[] args) {
		Stream.iterate("*", UnaryOperator.identity()).limit(5).collect(Collectors.joining(""));
		System.out.println(Something.INSTANCE.NUMBER2);

		Date date = new java.util.Date (2018 - 1900, 8, 27);
		System.out.println(date);
		
	}
}
