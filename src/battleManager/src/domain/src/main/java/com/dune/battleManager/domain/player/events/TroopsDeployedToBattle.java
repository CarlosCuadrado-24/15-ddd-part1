package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class TroopsDeployedToBattle extends DomainEvent {
    private final Integer Round;

    public TroopsDeployedToBattle(Integer round) {
        super(EventsEnum.TROOPS_DEPLOYED_TO_BATTLE.name());
        Round = round;
    }

    public Integer getRound() {
        return Round;
    }
}
