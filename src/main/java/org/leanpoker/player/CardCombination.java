package org.leanpoker.player;

import java.util.List;

/**
 *
 */
public class CardCombination {

    public static boolean hasPair(List<Rank> ranks) {
        Rank previous = ranks.get(0);
        for (int i = 1; i < ranks.size(); i++) {
            if(previous.equals(ranks.get(i))) {
                return true;
            }
            previous = ranks.get(i);
        }
        return false;
    }

}
