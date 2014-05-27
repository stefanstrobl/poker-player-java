package org.leanpoker.player;

import java.util.EnumSet;
import java.util.List;

public class SingleCardEvaluator {
    public static boolean hasCardHigherThanNine(List<Card> cards) {
        EnumSet.of(Rank.ACE, Rank.KING, Rank.QUEEN, Rank.KNAVE, Rank.TEN);
        for (Card card : cards) {
            if (card.getRank() == Rank.KING || card.getRank() == Rank.ACE) {
                return true;
            }
        }
        return false;
    }
}
