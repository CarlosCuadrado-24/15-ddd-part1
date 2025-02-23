package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import com.dune.shared.application.Request;

public class PreparePlayerToBattleRequest extends Request {

    private final Integer round;

    public PreparePlayerToBattleRequest(String aggregateId, Integer round) {
        super(aggregateId);
        this.round = round;
    }

    public Integer getRound() {
        return round;
    }
}
