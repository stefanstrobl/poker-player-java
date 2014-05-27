package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

//    @Test
//    public void testBetRequest() throws Exception {
//
//        JsonElement jsonElement = new JsonParser().parse("{\"minimum_raise\": \"220\", \"key2\": \"value2\"}");
//
//        assertEquals(220, Player.betRequest(jsonElement));
//
//    }
    @Test
    public void testGetMinRaiset() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\"minimum_raise\": \"220\", \"key2\": \"value2\"}");

        assertEquals(220, Player.getMinRaise(jsonElement.getAsJsonObject()));

    }
}
