package org.leanpoker.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlushEvaluator {
    public static boolean hasFlush(List<Card> cards) {
        Map<Suite, Integer> suit = new HashMap<>();
        for (Card card : cards) {
            Integer suitCount = 0;
            if (suit.containsKey(card.getSuite())) {
                suitCount = suit.get(card.getSuite());
                if (suitCount == 4) {
                    return true;
                }
            }
            suit.put(card.getSuite(), suitCount+1);
        }
        return false;
    }
}
