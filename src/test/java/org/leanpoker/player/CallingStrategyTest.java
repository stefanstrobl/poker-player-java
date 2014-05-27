package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertEquals;

public class CallingStrategyTest {

    @Test
    public void testDoCall() throws Exception {
        int call = new CallingStrategy().doCall(new BetRequestBuilder().get());
        assertEquals(240, call);
    }

    @Test
    public void testDoCallTiny() throws Exception {
        int call = new CallingStrategy().doCall(new BetRequestBuilder().currentBuyIn(71).ourBet(70).get());
        assertEquals(1, call);
    }

    @Test
    public void testDoCheckOnEqual() throws Exception {
        int call = new CallingStrategy().doCall(new BetRequestBuilder().currentBuyIn(70).ourBet(70).get());
        assertEquals(0, call);
    }
}