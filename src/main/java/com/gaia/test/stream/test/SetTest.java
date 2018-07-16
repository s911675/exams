package com.gaia.test.stream.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetTest {
	public static void mySetTest () {
		Set<String> oldSet = new HashSet<> ();
		oldSet.add("1");
		oldSet.add("2");
		oldSet.add("3");

		Set<String> newSet = new HashSet<> ();
		newSet.add("5");
		newSet.add("3");
		newSet.add("1");
		
		/*
		Set<String> copySet = set;
		
		set.removeAll(set2);
		
		System.out.println(set);
		System.out.println(set2);
		System.out.println(copySet);
		*/

		List<String> deleteList = oldSet.stream().filter(x->!newSet.contains(x)).collect(Collectors.toList());
		List<String> updateList = oldSet.stream().filter(x->newSet.contains(x)).collect(Collectors.toList());
		List<String> createList = newSet.stream().filter(x->!oldSet.contains(x)).collect(Collectors.toList());

		System.out.println(deleteList);
		System.out.println(updateList);
		System.out.println(createList);
		
	}
	public static void main(String[] args) {
		mySetTest ();
	}

}
