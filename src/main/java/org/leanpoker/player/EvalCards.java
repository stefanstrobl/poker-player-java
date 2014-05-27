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
            String rank = community_card.getAsJsonObject().get("rank").getAsString();
            String suit = community_card.getAsJsonObject().get("suit").getAsString();
            suits.add(new Card(Rank.fromString(rank), Suite.fromString(suit)));
        }
        return suits;
    }

    public List<Card> getMyCards() {
        JsonObject jsonPlayer = getJsonPlayer();
        return getCards(jsonPlayer.getAsJsonArray("hole_cards"));
    }

    public JsonObject getJsonPlayer() {
        JsonObject asJsonObject = json.getAsJsonObject();
        int positionOfPlayer = asJsonObject.get("in_action").getAsInt();
        JsonArray players = asJsonObject.getAsJsonArray("players");
        return players.get(positionOfPlayer).getAsJsonObject();
    }

    public List<Card> getAllCards() {
        List<Card> cards = getMyCards();
        cards.addAll(getCommunityCards());
        return cards;
    }
}
