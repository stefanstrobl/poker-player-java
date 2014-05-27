package org.leanpoker.player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SuiteTest {

    @Test
    public void testGetAsString() throws Exception {
        assertEquals(Suite.SPADES, Suite.fromString("spades"));

    }
}
