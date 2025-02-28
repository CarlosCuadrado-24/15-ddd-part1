package com.dune.battleManager.application.battle.InitializeBattle;

import com.dune.battleManager.application.battle.shared.PlayerRequest;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.Request;

import java.util.ArrayList;

public class InitializeBattleRequest extends Request {
    private ArrayList<PlayerRequest> players;

    public InitializeBattleRequest(String aggregateId, ArrayList<PlayerRequest> players) {
        super(aggregateId);
        this.players = players;
    }

    public ArrayList<PlayerRequest> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerRequest> players) {
        this.players = players;
    }
}
