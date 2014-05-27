package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

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
    public void callWithKing() throws Exception {
        assertEquals(240, Player.betRequest(EvalCardsTest.JSON_WITH_KING_AND_SIX.getAsJsonObject()));
    }

    @Test
    public void callWithAceHighcardOnPreFlush() throws Exception {
        assertEquals(40, Player.betRequest(new BetRequestBuilder()
                .addHoleCard(Suite.HEARTS, Rank.ACE).addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .currentBuyIn(60).ourBet(20)
                .get()));
    }

    @Test
    public void callWithAceHighcardPostFlush() throws Exception {
        assertEquals(40, Player.betRequest(new BetRequestBuilder()
                .addHoleCard(Suite.HEARTS, Rank.ACE).addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .createCommunityCard(Suite.HEARTS, Rank.FIVE)
                .createCommunityCard(Suite.SPADES, Rank.QUEEN)
                .createCommunityCard(Suite.CLUBS, Rank.TEN)
                .currentBuyIn(60).ourBet(20)
                .get()));
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

    @Test
    public void hasTripple() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .addHoleCard(Suite.CLUBS, Rank.SIX)
                .createCommunityCard(Suite.DIAMONDS, Rank.SIX)
                .createCommunityCard(Suite.DIAMONDS, Rank.NINE)
                .createCommunityCard(Suite.DIAMONDS, Rank.TEN);
        assertEquals(Integer.MAX_VALUE, Player.betRequest(betRequestBuilder.get()));
    }

    @Test
    public void hasLowPairPreFlop() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .addHoleCard(Suite.CLUBS, Rank.SIX);
        assertEquals(Integer.MAX_VALUE, Player.betRequest(betRequestBuilder.get()));
    }

    @Test
    public void hasLowPairPostFlop() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.DIAMONDS, Rank.FIVE)
                .addHoleCard(Suite.CLUBS, Rank.EIGHT)
                .createCommunityCard(Suite.DIAMONDS, Rank.FIVE)
                .createCommunityCard(Suite.DIAMONDS, Rank.NINE)
                .createCommunityCard(Suite.DIAMONDS, Rank.TEN);
        assertEquals(0, Player.betRequest(betRequestBuilder.get()));
    }

    @Test
    public void hasHighPairPostFlop() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.DIAMONDS, Rank.SIX)
                .addHoleCard(Suite.CLUBS, Rank.TEN)
                .createCommunityCard(Suite.DIAMONDS, Rank.EIGHT)
                .createCommunityCard(Suite.DIAMONDS, Rank.NINE)
                .createCommunityCard(Suite.DIAMONDS, Rank.TEN);
        assertEquals(Integer.MAX_VALUE, Player.betRequest(betRequestBuilder.get()));
    }

    @Test
    public void sixJack() {
        BetRequestBuilder betRequestBuilder = new BetRequestBuilder()
                .addHoleCard(Suite.HEARTS, Rank.SIX)
                .addHoleCard(Suite.HEARTS, Rank.KNAVE).currentBuyIn(20).ourBet(10);
        assertEquals(10, Player.betRequest(betRequestBuilder.get()));
    }
}
