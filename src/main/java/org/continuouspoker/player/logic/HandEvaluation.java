package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Card;
import org.continuouspoker.player.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.core.OrderComparator.sort;

public class HandEvaluation {

    public Float getScore(final Table table) {
        float score = 0;
        List<Card> handCards = table.getPlayers().get(table.getActivePlayer()).getCards();
        List<Card> communityCards = table.getCommunityCards();
        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(handCards);
        allCards.addAll(communityCards);
        allCards.sort(new HandMapping());

        Map<Integer, Long> rankGroups = allCards.stream().collect(
                Collectors.groupingBy(
                        (c) -> HandMapping.GetMapping(c), Collectors.counting()));

        Map<String, Long> typeGroups = allCards.stream().collect(
                Collectors.groupingBy(
                        (c) -> c.getClass(), Collectors.counting()));

        allCards.stream().
        System.out.print(allCards);
        return score;
    }
}
