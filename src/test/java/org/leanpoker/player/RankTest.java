package org.leanpoker.player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class RankTest {

    @Test
    public void testGetAsString() throws Exception {
        assertEquals(Rank.KING, Rank.fromString("K"));

    }

    @Test
    public void testGetAsStringNum() throws Exception {
        assertEquals(Rank.FIVE, Rank.fromString("5"));

    }
}
