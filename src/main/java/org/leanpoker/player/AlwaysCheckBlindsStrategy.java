package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 */
public class AlwaysCheckBlindsStrategy {

    public int alwaysCheckBlinds(JsonElement element) {
        JsonObject json = element.getAsJsonObject();
        int smallBlind = json.get("small_blind").getAsInt();
        int currentBuyIn = json.get("current_buy_in").getAsInt();
        int bigBlind = smallBlind * 2;

        // if we have bet the small blind, bet another blind if the currentBuyIn is not bigger as the big blind
        int dealerIndex = json.get("dealer").getAsInt();
        int ourIndex = json.get("in_action").getAsInt();
        boolean weHaveSmallBlind = dealerIndex + 1 == ourIndex;
        int toBet = weHaveSmallBlind ? smallBlind : bigBlind;

        // if the currentByIn is smaller als the big blind, bet the big blind (e.g. check a blind)
        return bigBlind >= currentBuyIn ? toBet : 0;
    }
}
