package org.leanpoker.player;

import com.google.gson.JsonElement;

public class GameStateResolver {
    public static boolean isPreFlop(JsonElement withoutCommunityCards) {
        JsonElement communityCards = withoutCommunityCards.getAsJsonObject().get("community_cards");
        return communityCards == null || communityCards.getAsJsonArray().size() == 0;
    }
}
