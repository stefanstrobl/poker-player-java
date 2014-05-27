package org.leanpoker.player;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SingleCardEvaluatorTest {
    @Test
    public void hasKing() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KING, null));
        assertTrue(SingleCardEvaluator.hasCardHigherThanNine(cards));
    }

    @Test
    public void hasAce() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, null));
        assertTrue(SingleCardEvaluator.hasCardHigherThanNine(cards));
    }

    @Test
    public void hasKnave() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KNAVE, null));
        assertTrue(SingleCardEvaluator.hasCardHigherThanNine(cards));
    }

    @Test
    public void noKingOrAce() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.NINE, null));
        assertFalse(SingleCardEvaluator.hasCardHigherThanNine(cards));
    }
}
