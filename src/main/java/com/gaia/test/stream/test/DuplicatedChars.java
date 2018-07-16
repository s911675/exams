package com.gaia.test.stream.test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicatedChars {
	public void printDuplicatedChars(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		System.out.println(value);
		
		List<String> list = value.chars().mapToObj(x -> String.valueOf((char) x)).collect(Collectors.toList());

		Map<String, Long> map = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		map.entrySet().stream().filter(x -> x.getValue() > 1)
				.forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));

	}

	public static void main(String[] args) {
		DuplicatedChars test = new DuplicatedChars();

		String value1 = "asdfasd";
		test.printDuplicatedChars(value1);
		
		test.printDuplicatedChars("1");
	}

}
