package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.leanpoker.player.util.BetRequestBuilder;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class EvalCardsTest {

    public static JsonElement jsonWithCommunityCards = new BetRequestBuilder()
            .createCommunityCard(Suite.SPADES, Rank.FOUR)
            .createCommunityCard(Suite.HEARTS, Rank.ACE)
            .createCommunityCard(Suite.CLUBS, Rank.SIX).get();

    public static final JsonElement JSON_WITH_KING_AND_SIX = new BetRequestBuilder().get();

    public static final JsonElement JSON_WITH_SIX_AND_FOUR = new BetRequestBuilder()
            .addHoleCard(Suite.SPADES, Rank.SIX).addHoleCard(Suite.HEARTS, Rank.FOUR).get();

    @Test
    public void getCommunityCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getCommunityCards();
        assertTrue(communitCards.contains(new Card(Rank.FOUR, Suite.SPADES)));
        assertTrue(communitCards.contains(new Card(Rank.ACE, Suite.HEARTS)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, Suite.CLUBS)));
    }

    @Test
    public void getMyCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getMyCards();
        assertTrue(communitCards.contains(new Card(Rank.KING, Suite.SPADES)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, Suite.HEARTS)));
    }

    @Test
    public void getAllCards() throws Exception {
        List<Card> communitCards = new EvalCards(jsonWithCommunityCards).getAllCards();
        assertTrue(communitCards.contains(new Card(Rank.FOUR, Suite.SPADES)));
        assertTrue(communitCards.contains(new Card(Rank.ACE, Suite.HEARTS)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, Suite.CLUBS)));
        assertTrue(communitCards.contains(new Card(Rank.KING, Suite.SPADES)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, Suite.HEARTS)));
    }

    @Test
    public void getAllCardsWithoutCommunityCards() throws Exception {
        List<Card> communitCards = new EvalCards(JSON_WITH_KING_AND_SIX).getAllCards();
        assertTrue(communitCards.contains(new Card(Rank.KING, Suite.SPADES)));
        assertTrue(communitCards.contains(new Card(Rank.SIX, Suite.HEARTS)));
    }

}
