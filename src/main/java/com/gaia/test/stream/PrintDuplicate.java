package com.gaia.test.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintDuplicate {

	public static void printDuplicatedCharsA(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		Map<String,Integer> map = new HashMap<>();
		for(int i=0,k=value.length();i<k;i++) {
			String ch = String.valueOf(value.charAt(i));
			Integer cnt = map.get(ch);
			if(cnt == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, cnt + 1);
			}
		}
		
		String result = "";
		
		for(String key : map.keySet()) {
			Integer cnt = map.get(key);
			if(cnt>1) {
				result += key;
			}
		}
		
		System.out.println(result);
	}
	
	public static void printDuplicatedCharsB(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		
		List<String> list = new ArrayList<> ();
		for(int i=0,k=value.length();i<k;i++) {
			list.add(String.valueOf(value.charAt(i)));
		}
		
		Map<String, Long> map = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				;
		
		String result = map.entrySet().stream().filter(a->a.getValue()>1).map(a->a.getKey()).collect(Collectors.joining());
		
		System.out.println(result);
				
	}
	
	public static void printDuplicatedCharsC(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		List<String> list = value.chars().mapToObj(a -> String.valueOf((char)a)).collect(Collectors.toList());
		
		Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		String result = map.entrySet().stream().filter(x -> x.getValue()>1).map(x -> x.getKey()).collect(Collectors.joining());
				/*
		String result = value.chars().mapToObj(a->String.valueOf(a)).collect(Collectors.toList()).stream()
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream().filter(tmpMap->tmpMap.getValue()>1).map(tmpMap->tmpMap.getKey()).collect(Collectors.joining());
		*/
		System.out.println(result);
	}
	public static void printDuplicatedCharsD(String value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		
		List<String> list = value.chars().mapToObj(x -> String.valueOf((char)x)).collect(Collectors.toList());
		Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		String result = map.entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).collect(Collectors.joining());
		
		System.out.println(result);
	}
	
	public static void printDuplicatedCharsE(String value) {
		List<String> list = value.chars().mapToObj(x -> String.valueOf((char)x)).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		String value1 = "asdfasd";
		printDuplicatedCharsD (value1);
	}

}
