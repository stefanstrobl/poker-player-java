package org.leanpoker.player.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.leanpoker.player.Rank;
import org.leanpoker.player.Suite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BetRequestBuilder {

    private String jsonStringWithWildcards = "{\n" +
            "    \"small_blind\": 10,                              \n" +
            "    \"current_buy_in\": 320,                          \n" +
            "    \"pot\": 400,                                     \n" +
            "    \"minimum_raise\": 240,                           \n" +
            "    \"dealer\": 1,                                    \n" +
            "    \"orbits\": 7,                                    \n" +
            "    \"in_action\": 1,                                 \n" +
            "    \"players\": [                                    \n" +
            "        {                                           \n" +
            "            \"id\": 0,                                \n" +
            "            \"name\": \"Albert\",                       \n" +
            "            \"status\": \"active\",                     \n" +
            "            \"version\": \"Default random player\",     \n" +
            "            \"stack\": 1010,                          \n" +
            "            \"bet\": 320                              \n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 1,                                \n" +
            "            \"name\": \"Bob\",\n" +
            "            \"status\": \"active\",\n" +
            "            \"version\": \"Default random player\",\n" +
            "            \"stack\": 1590,\n" +
            "            \"bet\": 80,\n" +
            "            \"hole_cards\": [                         \n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"name\": \"Chuck\",\n" +
            "            \"status\": \"out\",\n" +
            "            \"version\": \"Default random player\",\n" +
            "            \"stack\": 0,\n" +
            "            \"bet\": 0\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    List<JsonObject> communityCards = new ArrayList<>();
    private List<JsonObject> holeCards = new ArrayList<>();

    public JsonElement get() {
        JsonElement parse = new JsonParser().parse(jsonStringWithWildcards);
        if(holeCards.size() != 2) {
            addHoleCard(Suite.HEARTS, Rank.SIX);
            addHoleCard(Suite.SPADES, Rank.KING);
        }
        JsonObject object = parse.getAsJsonObject();

        JsonObject player = object.get("players").getAsJsonArray().get(1).getAsJsonObject();
        JsonArray hole_cards = player.get("hole_cards").getAsJsonArray();
        hole_cards.add(holeCards.get(0));
        hole_cards.add(holeCards.get(1));

        if(!communityCards.isEmpty()) {
            JsonArray array = new JsonArray();
            for (JsonObject communityCard : communityCards) {
                array.add(communityCard);
            }
            object.add("community_cards", array);
        }
        return object;
    }

    public BetRequestBuilder createCommunityCard(Suite suite, Rank rank) {
        JsonObject object = createCard(suite, rank);
        communityCards.add(object);
        return this;
    }

    public BetRequestBuilder addHoleCard(Suite suite, Rank rank) {
        JsonObject object = createCard(suite, rank);
        holeCards.add(object);
        return this;
    }

    private JsonObject createCard(Suite suite, Rank rank) {
        JsonObject object = new JsonObject();
        object.addProperty("suit", suite.getStringRepresentation());
        object.addProperty("rank", rank.getStringRepresentation());
        return object;
    }
}
