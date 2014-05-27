package org.leanpoker.player;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class CardCombination {

    public static boolean hasPair(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getRank().compareTo(o2.getRank());
            }
        });

        Rank previous = cards.get(0).getRank();
        for (int i = 1; i < cards.size(); i++) {
            Rank next = cards.get(i).getRank();
            if(previous.equals(next)) {
                return true;
            }
            previous = next;
        }
        return false;
    }

    public static boolean hasPair(List<Card> own, List<Card> community) {
        return hasOwnPair(own) || hasPairWithCommunity(own, community);
    }

    private static boolean hasPairWithCommunity(List<Card> own, List<Card> community) {
        for (Card ownCard : own) {
            for (Card commCard : community) {
                if(ownCard.getRank().equals(commCard.getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasOwnPair(List<Card> own) {
        return hasPair(own);
    }

    public static boolean hasTripple(List<Card> allCards) {
        HashMap<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : allCards) {
            Integer count = 0;
            Rank rank = card.getRank();
            if (rankCount.containsKey(rank)) {
                count = rankCount.get(rank);
            }
            rankCount.put(rank, count + 1);
        }
        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) {
                return true;
            }
        }
        return false;
    }
}
