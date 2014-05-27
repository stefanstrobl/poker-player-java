package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        try {

            JsonObject jsonObject = request.getAsJsonObject();
            int minRaise = getMinRaise(jsonObject);
            System.out.println(minRaise);

            return 100000;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    public static int getMinRaise(JsonObject jsonObject) {
        JsonElement minimum_raise = jsonObject.get("minimum_raise");
        return minimum_raise.getAsInt();
    }

    public static void showdown(JsonElement game) {
    }
}
