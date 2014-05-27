package org.leanpoker.player;

import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 */
public class AlwaysCheckBlindsStrategyTest {

    @Test
    public void testStrategyDoesNotApply() throws Exception {
        int bet = new AlwaysCheckBlindsStrategy().alwaysCheckBlinds(new BetRequestBuilder().get().getAsJsonObject());
        assertEquals(0, bet);
    }

    @Test
    public void testStrategyApplies() throws Exception {
        int bet = new AlwaysCheckBlindsStrategy().alwaysCheckBlinds(new BetRequestBuilder().smallBlind(10).currentBuyIn(20).get().getAsJsonObject());
        assertEquals(20, bet);
    }

    @Test
    public void testStrategyAppliesSmallBlind() throws Exception {
        int bet = new AlwaysCheckBlindsStrategy().alwaysCheckBlinds(new BetRequestBuilder().smallBlind(20).currentBuyIn(20).get().getAsJsonObject());
        assertEquals(40, bet);
    }
}
