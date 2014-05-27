package org.leanpoker.player;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum Rank {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    KNAVE("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A")
    ;

    private String stringRepresentation;
    private static Map<String, Rank> map = new HashMap<>();

    Rank(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    static {
        for (Rank rank : Rank.values()) {
            map.put(rank.getStringRepresentation(), rank);
        }
    }

    public static Rank fromString(String value) {
        return map.get(value);
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
