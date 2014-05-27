package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.List;

public class Player {

    static final String VERSION = "6";

    public static int betRequest(JsonElement request) {
        try {
            System.out.println(request.getAsJsonObject().toString());
            EvalCards evalCards = new EvalCards(request);

            List<Card> allCards = evalCards.getAllCards();
            List<Card> myCards = evalCards.getMyCards();
            List<Card> communityCards = evalCards.getCommunityCards();

            boolean preFlop = GameStateResolver.isPreFlop(request);
            if (CardCombination.hasTrippleOrPoker(myCards, communityCards)) {
                return Integer.MAX_VALUE;
            }
            if (FlushEvaluator.hasFlush(allCards)) {
                return Integer.MAX_VALUE;
            }
            if (new StraightEvaluator().evaluate(allCards)) {
                return Integer.MAX_VALUE;
            }
            if (!preFlop && CardCombination.hasPairHigherThanLimit(myCards, communityCards)) {
                return Integer.MAX_VALUE;
            }
            if (preFlop && CardCombination.hasPair(myCards, communityCards)) {
                return Integer.MAX_VALUE;
            }
            if (preFlop && SingleCardEvaluator.hasCardHigherThanNine(allCards)) {
                return new LimitedCallingStrategy().doCall(request.getAsJsonObject());
            }

            if(!preFlop && SingleCardEvaluator.isHighCard(myCards, communityCards)) {
                return new LimitedCallingStrategy().doCall(request.getAsJsonObject());
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
