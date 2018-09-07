package com.objectpartners;

import org.junit.Test;

/**
 * Tests the {@link EverythingTransfer} class.
 */
public class UserTest extends DtoTest<User> {

    @Override
    protected User getInstance() {
        return new User();
    }
    
    @Test
    public void testUser () throws Exception {
    	testGettersAndSetters();
    }

}