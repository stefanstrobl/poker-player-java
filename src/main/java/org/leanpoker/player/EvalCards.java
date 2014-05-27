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

    public List<Card> getCommunityCards() {
        if (json.getAsJsonObject().has("community_cards")) {

            JsonArray community_cards = json.getAsJsonObject().getAsJsonArray("community_cards");
            return getCards(community_cards);
        }
        return new ArrayList<>();
    }

    private List<Card> getCards(JsonArray community_cards) {
        List<Card> suits = new ArrayList<Card>();
        for (JsonElement community_card : community_cards) {
            String suit = community_card.getAsJsonObject().get("rank").getAsString();
            suits.add(new Card(Rank.fromString(suit), null));
        }
        return suits;
    }

    public List<Card> getMyCards() {
        JsonObject asJsonObject = json.getAsJsonObject();
        int positionOfPlayer = asJsonObject.get("in_action").getAsInt();
        JsonArray players = asJsonObject.getAsJsonArray("players");
        JsonObject jsonPlayer = players.get(positionOfPlayer).getAsJsonObject();
        return getCards(jsonPlayer.getAsJsonArray("hole_cards"));
    }

    public List<Card> getAllCards() {
        List<Card> cards = getMyCards();
        cards.addAll(getCommunityCards());
        return cards;
    }
}
