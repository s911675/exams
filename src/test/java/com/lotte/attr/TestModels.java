package com.lotte.attr;

import org.junit.Test;

import com.objectpartners.User;

public class TestModels {
	@Test
    public void testGetterSetterTest () throws Exception {
		GetterSetterTester<User> userTester = new GetterSetterTester.Builder<User>(new User())
				.build();
		
		userTester.testGettersAndSetters();
    }
}
