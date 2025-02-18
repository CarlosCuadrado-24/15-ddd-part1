package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class BattleStrengthCalculated extends DomainEvent {

    private final Integer Round;

    public BattleStrengthCalculated(Integer round) {
        super(EventsEnum.BATTLE_STRENGTH_CALCULATED.name());
        Round = round;
    }

    public Integer getRound() {
        return Round;
    }
}
