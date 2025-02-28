package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class TroopsDeployedToBattle extends DomainEvent {
    private  Integer round;

    public TroopsDeployedToBattle(Integer round) {
        super(EventsEnum.TROOPS_DEPLOYED_TO_BATTLE.name());
        this.round = round;
    }

    public TroopsDeployedToBattle() {
        super(EventsEnum.TROOPS_DEPLOYED_TO_BATTLE.name());
        this.round=0;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        round = round;
    }
}
