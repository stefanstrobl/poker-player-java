package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        try {

            JsonObject jsonObject = request.getAsJsonObject();
            JsonElement minimum_raise = jsonObject.get("minimum_raise");
            int minRaise = minimum_raise.getAsInt();

            return minRaise;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
