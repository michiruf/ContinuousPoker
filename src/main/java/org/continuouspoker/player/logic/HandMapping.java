package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Card;

import java.util.Comparator;

public class HandMapping implements Comparator<Card> {

    public static int getMapping(Card card) {
        if (card.getRank().getValue().matches("[0-9]+")) {
            return Integer.parseInt(card.getRank().getValue());
        }

        return switch (card.getRank().getValue()) {
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> 0;
        };
    }

    @Override
    public int compare(Card o1, Card o2) {
        return getMapping(o1) - getMapping((o2));
    }
}
