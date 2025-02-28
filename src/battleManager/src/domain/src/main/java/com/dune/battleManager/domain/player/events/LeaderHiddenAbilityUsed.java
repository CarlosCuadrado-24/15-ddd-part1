package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class LeaderHiddenAbilityUsed extends DomainEvent {

    private Integer round;

    public LeaderHiddenAbilityUsed(Integer round){
        super(EventsEnum.LEADER_HIDDEN_ABILITY_USED.name());
        this.round = round;
    }

    public LeaderHiddenAbilityUsed(){
        super(EventsEnum.LEADER_HIDDEN_ABILITY_USED.name());
        this.round = 0;
    }


    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
