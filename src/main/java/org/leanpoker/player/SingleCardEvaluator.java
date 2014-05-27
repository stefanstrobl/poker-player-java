package org.leanpoker.player;


import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class SingleCardEvaluator {
    public static boolean hasCardHigherThanNine(List<Card> cards) {
        EnumSet<Rank> limit = EnumSet.of(Rank.ACE, Rank.KING, Rank.QUEEN, Rank.KNAVE, Rank.TEN);
        for (Card card : cards) {
            if (limit.contains(card.getRank())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHighCard(List<Card> myCards, List<Card> communityCards) {
        Card max = Collections.max(communityCards, CardCombination.RANKINGCOMPARATOR);
        for (Card myCard : myCards) {
            if(myCard.getRank().compareTo(max.getRank()) > 0) {
                return true;
            }
        }
        return false;
    }
}
