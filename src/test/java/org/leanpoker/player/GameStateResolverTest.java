package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameStateResolverTest {

    @Test
    public void isPreFlop() throws Exception {
        JsonElement withoutCommunityCards = new BetRequestBuilder().get();

        assertTrue(GameStateResolver.isPreFlop(withoutCommunityCards));
    }

    @Test
    public void isPreFlopWithEmptyCommunityCards() throws Exception {
        JsonObject withoutCommunityCards = new BetRequestBuilder().get().getAsJsonObject();
        withoutCommunityCards.add("community_cards", new JsonArray());

        assertTrue(GameStateResolver.isPreFlop(withoutCommunityCards));
    }

    @Test
    public void isPostFlop() throws Exception {
        JsonElement withoutCommunityCards = new BetRequestBuilder().createCommunityCard(Suite.DIAMONDS, Rank.KING).get();

        assertFalse(GameStateResolver.isPreFlop(withoutCommunityCards));
    }
}