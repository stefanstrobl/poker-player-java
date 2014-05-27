package org.leanpoker.player;

import com.google.gson.JsonElement;
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
        assertEquals(0, Player.betRequest(EvalCardsTest.JSON_WITH_SIX_AND_FOUR));
    }

    @Test
    public void checkOnBigBlindOnly() throws Exception {
        JsonElement request = new BetRequestBuilder().smallBlind(10).currentBuyIn(20)
                .addHoleCard(Suite.HEARTS, Rank.SIX).addHoleCard(Suite.SPADES, Rank.FIVE).get();
        assertEquals(20, Player.betRequest(request));
    }

    @Test
    public void doNotCheckOverBigBlindOnly() throws Exception {
        JsonElement request = new BetRequestBuilder().smallBlind(10).currentBuyIn(40)
                .addHoleCard(Suite.HEARTS, Rank.SIX).addHoleCard(Suite.SPADES, Rank.FIVE).get();
        assertEquals(0, Player.betRequest(request));
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
