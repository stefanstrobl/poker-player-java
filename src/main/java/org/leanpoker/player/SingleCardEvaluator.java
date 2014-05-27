package org.leanpoker.player;

import java.util.List;

public class SingleCardEvaluator {
    public static boolean hasKingOrAce(List<Card> cards) {
        for (Card card : cards) {
            if (card.getRank() == Rank.KING || card.getRank() == Rank.ACE) {
                return true;
            }
        }
        return false;
    }
}
