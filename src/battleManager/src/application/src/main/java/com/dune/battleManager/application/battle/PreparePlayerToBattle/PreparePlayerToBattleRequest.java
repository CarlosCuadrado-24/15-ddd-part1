package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.Request;

public class PreparePlayerToBattleRequest extends Request {

    private final Integer round;
    private final Player player;

    public PreparePlayerToBattleRequest(String aggregateId, Integer round, Player player) {
        super(aggregateId);
        this.round = round;
        this.player = player;
    }

    public Integer getRound() {
        return round;
    }

    public Player getPlayer() {
        return player;
    }
}
