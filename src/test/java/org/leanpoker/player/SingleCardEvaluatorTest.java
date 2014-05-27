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
        assertTrue(SingleCardEvaluator.hasKingOrAce(cards));
    }

    @Test
    public void hasAce() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, null));
        assertTrue(SingleCardEvaluator.hasKingOrAce(cards));
    }

    @Test
    public void noKingOrAce() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.SIX, null));
        assertFalse(SingleCardEvaluator.hasKingOrAce(cards));
    }
}
