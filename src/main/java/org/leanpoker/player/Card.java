package org.leanpoker.player;

/**
 *
 */
public class Card {

    private Rank rank;
    private Suite suite;

    public Card(Rank rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public Rank getRank() {
        return rank;
    }

    public Suite getSuite() {
        return suite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        if (suite != card.suite) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank.hashCode();
        result = 31 * result + suite.hashCode();
        return result;
    }
}
