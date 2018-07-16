package com.gaia.test.stream.test;

public class Palindrome {

	public boolean isPalindrome(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder builder = new StringBuilder(value);

		return value.equals(builder.reverse().toString());
	}

	public static void main(String[] args) {
		Palindrome test = new Palindrome();

		String value1 = "asdf";
		String value2 = "asdfdsa";
		System.out.println(test.isPalindrome(value1));
		System.out.println(test.isPalindrome(value2));
	}
}
