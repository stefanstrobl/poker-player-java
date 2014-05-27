package org.leanpoker.player;

import com.google.gson.JsonObject;

/**
 *
 */
public class CallingStrategy {

    public int doCall(JsonObject json) {
        int currentBuyIn = json.get("current_buy_in").getAsInt();
        JsonObject player = new EvalCards(json).getJsonPlayer();
        int bet = player.get("bet").getAsInt();
        return bet < currentBuyIn ? currentBuyIn - bet : 0;
    }
}
