package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class EvalCards {
    private final JsonElement json;

    public EvalCards(JsonElement json) {
        this.json = json;
    }

    public List<String> getCommunityCards() {
        ArrayList<String> suits = new ArrayList<String>();
        JsonArray community_cards = json.getAsJsonObject().getAsJsonArray("community_cards");
        for (JsonElement community_card : community_cards) {
            String suit = community_card.getAsJsonObject().get("suit").getAsString();
            suits.add(suit);
        }
        return suits;
    }
}
