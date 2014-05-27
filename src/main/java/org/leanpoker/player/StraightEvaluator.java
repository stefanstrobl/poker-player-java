package org.leanpoker.player;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class StraightEvaluator {

    public boolean evaluate(List<Card> cards) {
        if(cards.size() < 5) {
            return false;
        }
        Collections.sort(cards, CardCombination.RANKINGCOMPARATOR);

        int previous = cards.get(0).getRank().ordinal();
        for (int i = 1; i < cards.size(); i++) {
            int next = cards.get(i).getRank().ordinal();
            if(previous + 1 == next) {
                previous = next;
            } else {
                return false;
            }
        }
        return true;
    }
}
