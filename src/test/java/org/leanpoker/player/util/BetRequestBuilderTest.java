package org.leanpoker.player.util;

import com.google.gson.JsonElement;
import org.junit.Test;
import org.leanpoker.player.Rank;
import org.leanpoker.player.Suite;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class BetRequestBuilderTest {

    @Test
    public void testCommunityCard() throws Exception {
        JsonElement communityCard = new BetRequestBuilder().createCommunityCard(Suite.CLUBS, Rank.ACE).get();

        assertTrue(communityCard.toString().contains("\"A\""));

    }


}
