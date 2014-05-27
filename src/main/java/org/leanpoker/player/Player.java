package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public class Player {

    static final String VERSION = "2";

    public static int betRequest(JsonElement request) {
        try {

            JsonObject jsonObject = request.getAsJsonObject();
            int minRaise = getMinRaise(jsonObject);
            System.out.println(minRaise);

            // if hasPair -> all In
            List<Card> allCards = new EvalCards(request).getAllCards();
            if (CardCombination.hasPair(allCards)) {
                return Integer.MAX_VALUE;
            }
            if (SingleCardEvaluator.hasKingOrAce(allCards)) {
                return Integer.MAX_VALUE;
            }

            // else fold
            return 20;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.MAX_VALUE;
    }

    public static int getMinRaise(JsonObject jsonObject) {
        JsonElement minimum_raise = jsonObject.get("minimum_raise");
        return minimum_raise.getAsInt();
    }

    public static void showdown(JsonElement game) {
    }
}
