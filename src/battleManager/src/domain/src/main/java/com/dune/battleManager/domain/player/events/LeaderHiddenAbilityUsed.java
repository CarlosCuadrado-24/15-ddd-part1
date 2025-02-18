package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class LeaderHiddenAbilityUsed extends DomainEvent {

    private final Integer Round;

    public LeaderHiddenAbilityUsed(Integer round){
        super(EventsEnum.LEADER_HIDDEN_ABILITY_USED.name());
        Round = round;
    }

    public Integer getRound() {
        return Round;
    }
}
