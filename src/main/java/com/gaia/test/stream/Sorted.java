package com.gaia.test.stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sorted {
	private static <T> void printList(List<T> list) {
		for (T t : list) {
			System.out.println(t);
		}
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(15, "Mahesh");
		map.put(10, "Suresh");
		map.put(30, "Nilesh");

		System.out.println("---Sort by Map Value---");
		map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
				.forEach(e -> System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue()));

		System.out.println("---Sort by Map Key---");
		map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
				.forEach(e -> System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue()));
		
		List<Integer> list = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
				.map(x -> x.getKey()).collect(Collectors.toList());

		printList(list);
		
		List<Integer> list2 = map.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toList()).stream()
				.sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());

		printList(list2);
		
		Comparator<Integer> comp = (a, b) -> a.compareTo(b);
		
		List<Integer> list3 = map.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toList()).stream()
				.sorted(comp.reversed()).collect(Collectors.toList());

		printList(list3);
	}
}
