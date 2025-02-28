package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class BattleStrengthCalculated extends DomainEvent {

    private Integer round;

    public BattleStrengthCalculated(Integer round) {
        super(EventsEnum.BATTLE_STRENGTH_CALCULATED.name());
        round = round;
    }

    public BattleStrengthCalculated() {
        super(EventsEnum.BATTLE_STRENGTH_CALCULATED.name());
        round = 0;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
