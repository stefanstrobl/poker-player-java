package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.List;

public class Player {

    static final String VERSION = "2";

    public static int betRequest(JsonElement request) {
        try {

            List<Card> allCards = new EvalCards(request).getAllCards();
            if (CardCombination.hasPair(allCards)) {
                return Integer.MAX_VALUE;
            }
            if (GameStateResolver.isPreFlop(request) && SingleCardEvaluator.hasKingOrAce(allCards)) {
                return Integer.MAX_VALUE;
            }

            // else fold
            return 20;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.MAX_VALUE;
    }

    public static void showdown(JsonElement game) {
    }
}
