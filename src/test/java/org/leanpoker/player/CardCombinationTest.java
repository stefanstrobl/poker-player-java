package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardCombinationTest {

    private JsonElement json = new JsonParser().parse("{\n" +
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
            "                    \"rank\": \"K\",                    \n" +
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

    @Test
    public void testHasPairFullList() throws Exception {
        List<Card> ranks = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.FIVE), asCard(Rank.EIGHT), asCard(Rank.KING), asCard(Rank.ACE));
        assertTrue(CardCombination.hasPair(ranks));
    }

    @Test
    public void testHasPairOwnVsCommunityShared() throws Exception {
        List<Card> own = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.FIVE));
        List<Card> community = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.KING), asCard(Rank.ACE));
        assertTrue(CardCombination.hasPair(own, community));
    }

    @Test
    public void testHasPairOwnVsCommunityOwn() throws Exception {
        List<Card> own = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.EIGHT));
        List<Card> community = Arrays.asList(asCard(Rank.THREE), asCard(Rank.KING), asCard(Rank.ACE));
        assertTrue(CardCombination.hasPair(own, community));
    }


    @Test
    public void testHasPairOwnVsCommunityInCommunity() throws Exception {
        List<Card> own = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.FIVE));
        List<Card> community = Arrays.asList(asCard(Rank.THREE), asCard(Rank.THREE), asCard(Rank.ACE));
        assertFalse(CardCombination.hasPair(own, community));
    }

    @Test
    public void testHasPairOwnVsCommunityNoPair() throws Exception {
        List<Card> own = Arrays.asList(asCard(Rank.EIGHT), asCard(Rank.FIVE));
        List<Card> community = Arrays.asList(asCard(Rank.THREE), asCard(Rank.TWO), asCard(Rank.ACE));
        assertFalse(CardCombination.hasPair(own, community));
    }

    private Card asCard(Rank rank) {
        return new Card(rank, null);
    }

}
