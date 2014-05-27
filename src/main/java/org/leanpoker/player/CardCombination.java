package org.leanpoker.player;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class CardCombination {

    public static final Comparator<Card> RANKINGCOMPARATOR = new Comparator<Card>() {
        @Override
        public int compare(Card o1, Card o2) {
            return o1.getRank().compareTo(o2.getRank());
        }
    };

    public static boolean hasPair(List<Card> cards) {
        Collections.sort(cards, RANKINGCOMPARATOR);

        Rank previous = cards.get(0).getRank();
        for (int i = 1; i < cards.size(); i++) {
            Rank next = cards.get(i).getRank();
            if(previous.equals(next)) {
                return true;
            }
            previous = next;
        }
        return false;
    }

    public static boolean hasPair(List<Card> own, List<Card> community) {
        return hasOwnPair(own) || hasPairWithCommunity(own, community);
    }

    private static boolean hasPairWithCommunity(List<Card> own, List<Card> community) {
        for (Card ownCard : own) {
            for (Card commCard : community) {
                if(ownCard.getRank().equals(commCard.getRank())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasOwnPair(List<Card> own) {
        return hasPair(own);
    }

    public static boolean hasTrippleOrPoker(List<Card> own, List<Card> community) {
        HashMap<Rank, Integer> communityRankCount = groupRanks(community);
        HashMap<Rank, Integer> ownRankCount = groupRanks(own);
        for (Map.Entry<Rank, Integer> ownRank : ownRankCount.entrySet()) {
            Rank rank = ownRank.getKey();

            Integer count = communityRankCount.get(rank);

            if (count != null && count + ownRank.getValue() >= 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPairHigherThanLimit(List<Card> own, List<Card> community) {
        Map<Rank, Integer> communityRanks = groupRanks(community);
        Map<Rank, Integer> ownRanks  = groupRanks(own);

        EnumSet<Rank> limit = EnumSet.of(Rank.ACE, Rank.KING, Rank.QUEEN, Rank.KNAVE, Rank.TEN, Rank.NINE, Rank.EIGHT, Rank.SEVEN, Rank.SIX);

        for (Map.Entry<Rank, Integer> ownRank : ownRanks.entrySet()) {
            Rank rank = ownRank.getKey();
            Integer ownCount = ownRank.getValue();
            Integer communityCount = 0;
            if (communityRanks.containsKey(rank)) {
                communityCount = communityRanks.get(rank);
            }

            if (limit.contains(rank) && communityCount + ownCount == 2) {
                return true;
            }
        }

        return false;
    }

    private static HashMap<Rank, Integer> groupRanks(List<Card> allCards) {
        HashMap<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : allCards) {
            Integer count = 0;
            Rank rank = card.getRank();
            if (rankCount.containsKey(rank)) {
                count = rankCount.get(rank);
            }
            rankCount.put(rank, count + 1);
        }
        return rankCount;
    }
}
