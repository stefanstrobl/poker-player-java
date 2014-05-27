package org.leanpoker.player;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum Suite {

    HEARTS("hearts"),
    SPADES("spades"),
    CLUBS("clubs"),
    DIAMONDS("diamonds")
    ;

    private String stringRepresentation;
    private static Map<String, Suite> map = new HashMap<>();

    Suite(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    static {
        for (Suite suite : Suite.values()) {
            map.put(suite.getStringRepresentation(), suite);
        }
    }

    public static Suite fromString(String value) {
        return map.get(value);
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
