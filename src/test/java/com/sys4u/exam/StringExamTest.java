package com.sys4u.exam;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringExamTest {
	String value = "asdfdsa";
	String value2 = "asdfda";
	
	StringExam app;

	@Before
	public void before() {
		app = new StringExam ();
	}

	@After
	public void after() {
	}
	
	@Test
	public void testPrintDuplicatedChars () {
		app.printDuplicatedChars(value);
	}

	@Test
	public void testIsPalindrome() throws Exception {
		assertEquals("회문이 아닙니다.", true, app.isPalindrome(value));
	}

	@Test
	public void testIsPalindromeFalse() throws Exception {
		assertEquals("false 여야 한다.", false, app.isPalindrome(value2));
	}

	@Test
	public void testRemoveDuplicated() throws Exception {
		assertEquals("asdf 여야 한다.", "asdf", app.removeDuplicated(value));
	}

	@Test
	public void testIsIdenticalElements() throws Exception {
		assertEquals("true 여야 함다.", true, app.isIdenticalElements(value, value2));
	}

	@Test
	public void testIsIdenticalElementsFalse() throws Exception {
		assertEquals("", false, app.isIdenticalElements(value, value2 + "X"));
	}
}
