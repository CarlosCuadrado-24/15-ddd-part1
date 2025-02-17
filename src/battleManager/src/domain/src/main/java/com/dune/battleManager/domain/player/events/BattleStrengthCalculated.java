package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class BattleStrengthCalculated extends DomainEvent {

    private final Integer battleStrength;
    private final Integer battleReadyTroops;
    private final String effectIntrigueCard;
    private final Integer stackIntrigueCard;
    private final String effectPermanentAbilityLeader;
    private final Integer stackPermanentAbilityLeader;

    public BattleStrengthCalculated(Integer battleStrength, Integer battleReadyTroops, String effectIntrigueCard, Integer stackIntrigueCard, String effectPermanentAbilityLeader, Integer stackPermanentAbilityLeader) {
        super(EventsEnum.BATTLE_STRENGTH_CALCULATED.name());
        this.battleStrength = battleStrength;
        this.battleReadyTroops = battleReadyTroops;
        this.effectIntrigueCard = effectIntrigueCard;
        this.stackIntrigueCard = stackIntrigueCard;
        this.effectPermanentAbilityLeader = effectPermanentAbilityLeader;
        this.stackPermanentAbilityLeader = stackPermanentAbilityLeader;
    }

    public Integer getBattleStrength() {
        return battleStrength;
    }

    public Integer getBattleReadyTroops() {
        return battleReadyTroops;
    }

    public String getEffectIntrigueCard() {
        return effectIntrigueCard;
    }

    public Integer getStackIntrigueCard() {
        return stackIntrigueCard;
    }

    public String getEffectPermanentAbilityLeader() {
        return effectPermanentAbilityLeader;
    }

    public Integer getStackPermanentAbilityLeader() {
        return stackPermanentAbilityLeader;
    }

}
