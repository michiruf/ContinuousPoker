package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;

public class Strategy {

    public static final String teamname = "kauderwelschkodierer";

    public Bet decide(final Table table) {
        System.out.println(table);

        // var me = table.getPlayers().get(table.getActivePlayer());
        return RandomStrategy.calc(table);
    }
}
