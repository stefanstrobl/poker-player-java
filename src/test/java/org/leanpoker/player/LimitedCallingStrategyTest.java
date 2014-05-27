package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertEquals;

public class LimitedCallingStrategyTest {

    @Test
    public void testDoCallExactLimit() throws Exception {
        int call = new LimitedCallingStrategy().doCall(new BetRequestBuilder()
                .smallBlind(10).ourBet(20).currentBuyIn(60).get());
        assertEquals(40, call);
    }

    @Test
    public void testDoCallTiny() throws Exception {
        int call = new LimitedCallingStrategy().doCall(new BetRequestBuilder().currentBuyIn(71).ourBet(70).get());
        assertEquals(1, call);
    }

    @Test
    public void testDoCallHuge() throws Exception {
        int call = new LimitedCallingStrategy().doCall(new BetRequestBuilder().currentBuyIn(500).ourBet(20).get());
        assertEquals(0, call);
    }

    @Test
    public void testDoCheckOnEqual() throws Exception {
        int call = new LimitedCallingStrategy().doCall(new BetRequestBuilder().currentBuyIn(70).ourBet(70).get());
        assertEquals(0, call);
    }
}