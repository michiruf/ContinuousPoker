package org.continuouspoker.player.strategy;

import org.continuouspoker.player.logic.HandEvaluation;
import org.continuouspoker.player.strategy.random.RandomStrategy;
import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;

public class Strategy {

    public static final String teamname = "kauderwelschkodierer";

    public Bet decide(final Table table) {
        System.out.println(table);

        var me = table.getPlayers().get(table.getActivePlayer());
        var handValue = HandEvaluation.getScore(table);
        handValue = handValue * handValue;
        var stackedHandValue = me.getStack() * handValue;
        return new Bet().bet((int) stackedHandValue);
        // return RandomStrategy.calc(table);
    }
}
