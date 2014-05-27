package org.leanpoker.player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void noKingOrAce() throws Exception {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.NINE, null));
        assertFalse(SingleCardEvaluator.hasCardHigherThanNine(cards));
    }

    @Test
    public void isHighCard() throws Exception {
        assertTrue(SingleCardEvaluator.isHighCard(
                Arrays.asList(asCard(Rank.SIX), asCard(Rank.ACE)),
                Arrays.asList(asCard(Rank.QUEEN))));
    }

    @Test
    public void isNotHighCard() throws Exception {
        assertFalse(SingleCardEvaluator.isHighCard(
                Arrays.asList(asCard(Rank.SIX), asCard(Rank.QUEEN)),
                Arrays.asList(asCard(Rank.ACE), asCard(Rank.KING))));
    }

    private Card asCard(Rank rank) {
        return new Card(rank, null);
    }

}
