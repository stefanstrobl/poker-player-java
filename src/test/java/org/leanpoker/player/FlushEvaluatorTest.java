package org.leanpoker.player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlushEvaluatorTest {

    @Test
    public void testNoHasFlush() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        assertFalse(FlushEvaluator.hasFlush(cards));
    }

    @Test
    public void testHasFlush() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KING, Suite.SPADES));
        cards.add(new Card(Rank.QUEEN, Suite.SPADES));
        cards.add(new Card(Rank.ACE, Suite.SPADES));
        cards.add(new Card(Rank.NINE, Suite.SPADES));
        cards.add(new Card(Rank.SIX, Suite.SPADES));

        assertTrue(FlushEvaluator.hasFlush(cards));
    }

    @Test
    public void testHasNearlyFlush() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.QUEEN, Suite.SPADES));
        cards.add(new Card(Rank.ACE, Suite.SPADES));
        cards.add(new Card(Rank.NINE, Suite.SPADES));
        cards.add(new Card(Rank.SIX, Suite.SPADES));

        assertFalse(FlushEvaluator.hasFlush(cards));
    }
}