package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class TroopsDeployedToBattle extends DomainEvent {

    private final String combatIntrigueCardName;
    private final Integer combatIntrigueCardStack;
    private final String permanentAbility;
    private final Integer permanentAbilityStack;

    public TroopsDeployedToBattle(String combatIntrigueCardName, Integer combatIntrigueCardStack, String permanentAbility, Integer permanentAbilityStack) {
        super(EventsEnum.TROOPS_DEPLOYED_TO_BATTLE.name());
        this.combatIntrigueCardName = combatIntrigueCardName;
        this.combatIntrigueCardStack = combatIntrigueCardStack;
        this.permanentAbility = permanentAbility;
        this.permanentAbilityStack = permanentAbilityStack;
    }

    public String getCombatIntrigueCardName() {
        return combatIntrigueCardName;
    }

    public Integer getCombatIntrigueCardStack() {
        return combatIntrigueCardStack;
    }

    public String getPermanentAbility() {
        return permanentAbility;
    }

    public Integer getPermanentAbilityStack() {
        return permanentAbilityStack;
    }
}
