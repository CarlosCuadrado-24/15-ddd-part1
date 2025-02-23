package com.dune.battleManager.application.battle.InitializeBattle;

import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.Request;

import java.util.ArrayList;

public class InitializeBattleRequest extends Request {
    private final ArrayList<Player> players;

    public InitializeBattleRequest(String aggregateId, ArrayList<Player> players) {
        super(aggregateId);
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
