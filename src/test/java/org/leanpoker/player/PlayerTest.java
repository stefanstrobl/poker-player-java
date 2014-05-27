package org.leanpoker.player;

import org.junit.Test;

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
}
