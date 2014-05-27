package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class EvalCards {
    private final JsonElement json;

    public EvalCards(JsonElement json) {
        this.json = json;
    }

    public List<Rank> getCommunityCards() {
        if (json.getAsJsonObject().has("community_cards")) {

            JsonArray community_cards = json.getAsJsonObject().getAsJsonArray("community_cards");
            return getCards(community_cards);
        }
        return new ArrayList<>();
    }

    private List<Rank> getCards(JsonArray community_cards) {
        List<Rank> suits = new ArrayList<Rank>();
        for (JsonElement community_card : community_cards) {
            String suit = community_card.getAsJsonObject().get("rank").getAsString();
            suits.add(Rank.fromString(suit));
        }
        return suits;
    }

    public List<Rank> getMyCards() {
        JsonObject asJsonObject = json.getAsJsonObject();
        int positionOfPlayer = asJsonObject.get("in_action").getAsInt();
        JsonArray players = asJsonObject.getAsJsonArray("players");
        JsonObject jsonPlayer = players.get(positionOfPlayer).getAsJsonObject();
        return getCards(jsonPlayer.getAsJsonArray("hole_cards"));
    }

    public List<Rank> getAllCards() {
        List<Rank> cards = getMyCards();
        cards.addAll(getCommunityCards());
        return cards;
    }
}
