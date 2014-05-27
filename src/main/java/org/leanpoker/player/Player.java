package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.List;

public class Player {

    static final String VERSION = "3";

    public static int betRequest(JsonElement request) {
        try {

            EvalCards evalCards = new EvalCards(request);

            List<Card> allCards = evalCards.getAllCards();
            List<Card> myCards = evalCards.getMyCards();
            List<Card> communityCards = evalCards.getCommunityCards();

            if (CardCombination.hasPair(myCards, communityCards)) {
                return Integer.MAX_VALUE;
            }
            if (GameStateResolver.isPreFlop(request) && SingleCardEvaluator.hasKingOrAce(allCards)) {
                return Integer.MAX_VALUE;
            }
            if (FlushEvaluator.hasFlush(allCards)) {
                return Integer.MAX_VALUE;
            }

            // else fold
            return new AlwaysCheckBlindsStrategy().alwaysCheckBlinds(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.MAX_VALUE;
    }

    public static void showdown(JsonElement game) {
    }
}
