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
            "                {\n" +
            "                    \"rank\": \"6\",                    \n" +
            "                    \"suit\": \"hearts\"                \n" +
            "                },\n" +
            "                {\n" +
            "                    \"rank\": \"K\",\n" +
            "                    \"suit\": \"spades\"\n" +
            "                }\n" +
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

    public JsonElement get() {
        JsonElement parse = new JsonParser().parse(jsonStringWithWildcards);
        JsonObject object = parse.getAsJsonObject();
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
        JsonObject object = new JsonObject();
        object.addProperty("suit", suite.getStringRepresentation());
        object.addProperty("rank", rank.getStringRepresentation());
        communityCards.add(object);
        return this;
    }
}
