package com.gaia.test.newbie;

public class A02 {
	public boolean isPalindrome(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		StringBuilder sb = new StringBuilder(value);

		return value.equals(sb.reverse().toString());
	}

	public static void main(String[] args) {
		String value = "asdfsf";
		String value2 = "12321";
		
		A02 app = new A02 ();
		System.out.println(app.isPalindrome(value));
		System.out.println(app.isPalindrome(value2));
	}
}
