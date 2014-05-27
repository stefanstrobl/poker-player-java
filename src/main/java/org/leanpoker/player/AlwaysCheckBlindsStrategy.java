package org.leanpoker.player;

import com.google.gson.JsonObject;

/**
 *
 */
public class AlwaysCheckBlindsStrategy {

    public int alwaysCheckBlinds(JsonObject json) {
        int smallBlind = json.get("small_blind").getAsInt();
        int currentBuyIn = json.get("current_buy_in").getAsInt();
        int bigBlind = smallBlind * 2;

        return bigBlind >= currentBuyIn ? bigBlind : 0;
    }
}
