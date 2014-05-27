package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class EvalCardsTest {

    private JsonElement jsonWithCommunityCards = new JsonParser().parse("{\n" +
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
            "    ],\n" +
            "    \"community_cards\": [                            \n" +
            "        {\n" +
            "            \"rank\": \"4\",\n" +
            "            \"suit\": \"spades\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"rank\": \"A\",\n" +
            "            \"suit\": \"hearts\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"rank\": \"6\",\n" +
            "            \"suit\": \"clubs\"\n" +
            "        }\n" +
            "    ]\n" +
            "}");

    private JsonElement jsonWithoutCommunityCards = new JsonParser().parse("{\n" +
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
            "}");

    @Test
    public void getCommunityCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getCommunityCards();
        assertTrue(communitCards.contains(new Card(Rank.FOUR, null)));
        assertTrue(communitCards.contains(new Card(Rank.ACE, null)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, null)));
    }

    @Test
    public void getMyCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getMyCards();
        assertTrue(communitCards.contains(new Card(Rank.KING, null)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, null)));
    }

    @Test
    public void getAllCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getAllCards();
        assertTrue(communitCards.contains(new Card(Rank.FOUR, null)));
        assertTrue(communitCards.contains(new Card(Rank.ACE, null)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, null)));
        assertTrue(communitCards.contains(new Card(Rank.KING, null)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, null)));
    }

    @Test
    public void getAllCardsWithoutCommunityCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithoutCommunityCards).getAllCards();
        assertTrue(communitCards.contains(new Card(Rank.KING, null)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, null)));
    }

    // community cards leer
}
