package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;

public class RandomStrategy {
    public static Bet calc(Table table) {
        var r = (int) (Math.random() * 10.0);
        if (r == 0)
            return new Bet().bet(0);

        return new Bet().bet(table.getMinimumBet());
    }
}
