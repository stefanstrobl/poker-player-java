package org.leanpoker.player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        if(hasPair(own)) {
            return true;
        }
        for (Card ownCard : own) {
            for (Card commCard : community) {
                if(ownCard.getRank().equals(commCard.getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

}
