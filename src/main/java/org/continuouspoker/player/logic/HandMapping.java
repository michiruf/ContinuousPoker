package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Card;

import java.util.Comparator;

public class HandMapping implements Comparator<Card> {

    public static int GetMapping(Card card) {
        int rank = 0;
        if (card.getRank().getValue().matches("[0-9]+")) {
            rank = Integer.parseInt(card.getRank().getValue());
        }
        else {
            switch(card.getRank().getValue()) {
                case "J": rank = 11; break;
                case "Q": rank = 12; break;
                case "K": rank = 13; break;
                case "A": rank = 14; break;
            }
        }
        return rank;
    }
    @Override
    public int compare(Card o1, Card o2) {
        return GetMapping(o1) - GetMapping((o2));
    }
}
