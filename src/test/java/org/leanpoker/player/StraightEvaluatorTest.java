package org.leanpoker.player;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StraightEvaluatorTest {

    @Test
    public void testEvaluateTooShort() throws Exception {
        assertFalse(new StraightEvaluator().evaluate(Arrays.asList(asCard(Rank.SIX))));
    }

    @Test
    public void testEvaluateStrait() throws Exception {
        assertTrue(new StraightEvaluator().evaluate(Arrays.asList(
                asCard(Rank.SEVEN),
                asCard(Rank.TEN),
                asCard(Rank.EIGHT),
                asCard(Rank.NINE),
                asCard(Rank.SIX)
        )));
    }

    @Test
    public void testEvaluateStraitWithHighValues() throws Exception {
        assertTrue(new StraightEvaluator().evaluate(Arrays.asList(
                asCard(Rank.QUEEN),
                asCard(Rank.TEN),
                asCard(Rank.KNAVE),
                asCard(Rank.NINE),
                asCard(Rank.KING)
        )));
    }

    @Test
    public void testEvaluateStraitAlmost() throws Exception {
        assertFalse(new StraightEvaluator().evaluate(Arrays.asList(
                asCard(Rank.QUEEN),
                asCard(Rank.TEN),
                asCard(Rank.KNAVE),
                asCard(Rank.NINE),
                asCard(Rank.ACE)
        )));
    }

    private Card asCard(Rank six) {
        return new Card(six, null);
    }
}