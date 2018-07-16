package com.gaia.test.stream;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class LambdaTest {
	public static List<Person> createPerson () {
		List<Person> person = new ArrayList<> ();
		
		person.add(new Person("John", Gender.MALE, 17));
		person.add(new Person("John", Gender.MALE, 2));
		person.add(new Person("Merry", Gender.FEMALE, 17));
		person.add(new Person("Merry", Gender.FEMALE, 2));
		person.add(new Person("Will", Gender.MALE, 17));
		person.add(new Person("Will", Gender.MALE, 2));
		person.add(new Person("Sally", Gender.FEMALE, 17));
		person.add(new Person("Sally", Gender.FEMALE, 2));
		
		
		return person;
	}
	
	public static void peopleTest () {
		List<Person> people = createPerson();
		
		List<String> names = people.stream()
			.filter(person -> person.getAge() > 15 && person.getGender() == Gender.FEMALE)
			.map(person -> person.getName().toUpperCase())
			.collect(toList());
		
		System.out.println(people);
		System.out.println("names\n"+ names);
	}
	
	public static void stringArraysTest () {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		
		myList
			.stream()
			.filter(s -> s.startsWith("c"))
			.map(s -> s.toUpperCase())
			.forEach(System.out::println);

	}
	
	public static void intArraysTest () {
		Arrays
		.stream(new int[] {1,9,5,3})
		.map(x -> x * 2)
		.average()
		.ifPresent(System.out::println);
	}
	
	public static void subStringTest () {
		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(1))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);  // 3
	}
	
	public static void main(String[] args) {
//		peopleTest ();
//		stringArraysTest ();
//		intArraysTest ();
//		subStringTest ();
	}
}
