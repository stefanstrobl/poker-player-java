package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void pairBetAll() throws Exception {
        assertEquals(Integer.MAX_VALUE, Player.betRequest(EvalCardsTest.jsonWithCommunityCards.getAsJsonObject()));
    }

    @Test
    public void noPairFold() throws Exception {
        assertEquals(20, Player.betRequest(EvalCardsTest.JSON_WITH_SIX_AND_FOUR.getAsJsonObject()));
    }

    @Test
    public void noFoldWithKing() throws Exception {
        assertEquals(Integer.MAX_VALUE, Player.betRequest(EvalCardsTest.JSON_WITH_KING_AND_SIX.getAsJsonObject()));
    }

    @Test
    public void hasFlush() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .addHoleCard(Suite.DIAMONDS, Rank.SEVEN)
                .createCommunityCard(Suite.DIAMONDS, Rank.EIGHT)
                .createCommunityCard(Suite.DIAMONDS, Rank.NINE)
                .createCommunityCard(Suite.DIAMONDS, Rank.TEN);
        assertEquals(Integer.MAX_VALUE, Player.betRequest(betRequestBuilder.get()));
    }
}
