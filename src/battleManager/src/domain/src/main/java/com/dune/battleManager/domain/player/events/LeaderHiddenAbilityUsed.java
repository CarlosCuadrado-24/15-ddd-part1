package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class LeaderHiddenAbilityUsed extends DomainEvent {

    private final String combatIntrigueCard;

    public LeaderHiddenAbilityUsed(String combatIntrigueCard) {
        super(EventsEnum.LEADER_HIDDEN_ABILITY_USED.name());
        this.combatIntrigueCard = combatIntrigueCard;
    }

    public String getCombatIntrigueCard() {
        return combatIntrigueCard;
    }

}
