package com.gaia.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupBy {
	public static void printDuplicatedChars(String value) {

		List<Character> list = new ArrayList<>();

		for (Character c : value.toCharArray()) {
			list.add(c);
		}

		Map<Character, Long> result = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		for (Map.Entry<Character, Long> entry : result.entrySet()) {
			if (entry.getValue() < 2) {
				continue;
			} else {
				System.out.println(String.format("%C : %d", entry.getKey(), entry.getValue()));
			}
		}
	}

	public static boolean isPalindrome(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		Boolean result = Boolean.TRUE;

		String reverseValue = new StringBuilder(value).reverse().toString();

		for (int i = 0, k = value.length() / 2; i < k; i++) {
			if (value.charAt(i) != reverseValue.charAt(i)) {
				result = Boolean.FALSE;
			}
		}

		return result;
	}

	public static String removeDuplicated(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		String result = value.chars().mapToObj(c -> String.valueOf((char) c)).distinct().collect(Collectors.joining());

		return result;
	}

	public static int addIntData(int num1, int num2) {
		System.out.println(String.format("addIntData Called num1=%d, num2=%d", num1, num2));
		return num1 + num2;
	}

	public static void reduce() {
		int[] array = { 23, 43, 56, 97, 32 };
		Arrays.stream(array).reduce((x, y) -> x + y).ifPresent(s -> System.out.println(s + ":"));
		Arrays.stream(array).reduce(Integer::sum).ifPresent(s -> System.out.println(s));
		Arrays.stream(array).reduce(GroupBy::addIntData).ifPresent(s -> System.out.println(s));
	}

	public static void streamTest() {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

		myList
			.stream()
			.filter(s -> s.startsWith("c"))
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);
		
		System.out.println(myList.stream().filter(s-> s.startsWith("a")).reduce("X", String::concat));
		
		List<String> list = myList.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		System.out.println(list);
		
		System.out.println("a".compareTo("b"));
		System.out.println("a".compareTo("a"));
		System.out.println("b".compareTo("a"));
	}

	public static void main(String[] args) {
		String a = "asdfsdf";
		String b = "123321";
		String c = "1233321";

		// printDuplicatedChars (a);

		// System.out.println(isPalindrome(a));
		// System.out.println(isPalindrome(b));
		// System.out.println(isPalindrome(c));
		/*
		 * System.out.println(removeDuplicated(a));
		 * System.out.println(removeDuplicated(b));
		 * System.out.println(removeDuplicated(c));
		 */

		// reduce();

		streamTest();
	}
}
