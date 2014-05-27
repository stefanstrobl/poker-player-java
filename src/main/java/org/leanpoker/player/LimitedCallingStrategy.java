package org.leanpoker.player;

import com.google.gson.JsonObject;

/**
 *
 */
public class LimitedCallingStrategy extends CallingStrategy {

    @Override
    public int doCall(JsonObject json) {
        int call = super.doCall(json);

        int smallBlind = json.get("small_blind").getAsInt();

        return call <= smallBlind * 4 ? call : 0;
    }
}
