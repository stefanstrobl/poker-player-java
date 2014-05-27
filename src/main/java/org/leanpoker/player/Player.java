package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        try {

            JsonObject jsonObject = request.getAsJsonObject();
            JsonElement minimum_raise = jsonObject.get("minimum_raise");
            int minRaise = minimum_raise.getAsInt();

            return 1000;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
