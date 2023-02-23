package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Card;
import org.continuouspoker.player.model.Suit;
import org.continuouspoker.player.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.core.OrderComparator.sort;

public class HandEvaluation {

    public static float getScore(final Table table) {
        var handCards = table.getPlayers().get(table.getActivePlayer()).getCards();
        var communityCards = table.getCommunityCards();
        var allCards = new ArrayList<Card>();
        allCards.addAll(handCards);
        allCards.addAll(communityCards);


        // TODO Might be optional
        allCards.sort(new HandMapping());
        System.out.print(allCards);

        // Define result variables
        var royalFlush = false;
        var straightFlush = false;
        var fourOfAKind = false;
        var fullHouse = false;
        var flush = false;
        var straight = false;
        var threeOfAKind = false;
        var twoPair = false;
        var pair = false;
        var kicker = -1;

        Map<Integer, Long> rankGroups = allCards.stream().collect(
                Collectors.groupingBy(HandMapping::getMapping, Collectors.counting()));
        Map<Suit, Long> suitGroups = allCards.stream().collect(
                Collectors.groupingBy(Card::getSuit, Collectors.counting()));

        // TODO royalFlush = false;
        // TODO straightFlush = false;
        if (rankGroups.entrySet().stream().anyMatch(e1 -> e1.getValue() >= 4))
            fourOfAKind = true;
        else if (rankGroups.entrySet().stream().anyMatch(e1 -> e1.getValue() >= 2 &&
                rankGroups.entrySet().stream().anyMatch(e2 -> !Objects.equals(e1.getKey(), e2.getKey()) && e2.getValue() >= 3))
        )
            fullHouse = true;
        else if (suitGroups.containsValue(5L) || suitGroups.containsValue(6L) || suitGroups.containsValue(7L))
            flush = true;
        else if (countCardsInARow(allCards) >= 5)
            straight = true;
        else if (rankGroups.containsValue(3L))
            threeOfAKind = true;
        else if (rankGroups.entrySet().stream().filter(e -> e.getValue() == 2).count() >= 2)
            twoPair = true;
        else if (rankGroups.containsValue(2L))
            pair = true;
        else
            kicker = HandMapping.getMapping(allCards.get(0));

        return evaluateHand(
                royalFlush,
                straightFlush,
                fourOfAKind,
                fullHouse,
                flush,
                straight,
                threeOfAKind,
                twoPair,
                pair,
                kicker);
    }

    public static int countCardsInARow(List<Card> cards) {
        var cardsSorted = cards.stream()
                .map(HandMapping::getMapping)
                .sorted()
                .toList();

        var c = 0;
        var maxC = 0;
        for (var i = 0; i < cardsSorted.size() - 1; i++) {
            if (cardsSorted.get(i) + 1 == cardsSorted.get(i + 1))
                c++;
            else
                c = 0;
            maxC = Math.min(maxC, c);
        }

        return maxC;
    }

    public static float evaluateHand(
            boolean royalFlush,
            boolean straightFlush,
            boolean fourOfAKind,
            boolean fullHouse,
            boolean flush,
            boolean straight,
            boolean threeOfAKind,
            boolean twoPair,
            boolean pair,
            int kicker) {
//        if (royalFlush)
//            return 100000000;
//        if (straightFlush)
//            return 714f;
//        if (fourOfAKind)
//            return 417f;
//        if (fullHouse)
//            return 6.9f;
//        if (flush)
//            return 5.2f;
//        if (straight)
//            return 2.5f;
//        if (threeOfAKind)
//            return 0.5f;
//        if (twoPair)
//            return 0;
//        if (pair)
//            return 0.25f;
//        return kicker * 0.2f;
        if (royalFlush)
            return 1f;
        if (straightFlush)
            return 1f - 0.000014f;
        if (fourOfAKind)
            return 1f - 0.000024f;
        if (fullHouse)
            return 1f - 0.001441f;
        if (flush)
            return 1f - 0.0019f;
        if (straight)
            return 1f - 0.0039f;
        if (threeOfAKind)
            return 1f - 0.021f;
        if (twoPair)
            return 1f - 0.047f;
        if (pair)
            return 1f - 0.42f;
        return (kicker / 14f) * (1f - 0.5f);
    }
}
