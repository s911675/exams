package com.gaia.test.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Person {
	private String name;
	private Gender gender;
	private int age;
}
