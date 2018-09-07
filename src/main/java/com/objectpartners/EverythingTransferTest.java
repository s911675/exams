package com.objectpartners;

import org.junit.Test;

/**
 * Tests the {@link EverythingTransfer} class.
 */
public class EverythingTransferTest extends DtoTest<EverythingTransfer> {

    @Override
    protected EverythingTransfer getInstance() {
        return new EverythingTransfer();
    }
    
    @Test
    public void testEverythinsTransferTest () throws Exception {
    	testGettersAndSetters();
    }

}