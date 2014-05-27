package org.leanpoker.player;

import com.google.gson.JsonElement;

public class GameStateResolver {
    public static boolean isPreFlop(JsonElement withoutCommunityCards) {
        return !withoutCommunityCards.getAsJsonObject().has("community_cards");
    }
}
