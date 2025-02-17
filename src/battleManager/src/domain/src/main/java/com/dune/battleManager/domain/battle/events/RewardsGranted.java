package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class RewardsGranted extends DomainEvent {

    private final Integer victoryPoints;
    private final Integer troops;
    private final Integer resources;

    public RewardsGranted(String name, Integer victoryPoints, Integer troops, Integer resources) {
        super(EventsEnum.REWARDS_GRANTED.name());
        this.victoryPoints = victoryPoints;
        this.troops = troops;
        this.resources = resources;
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    public Integer getTroops() {
        return troops;
    }

    public Integer getResources() {
        return resources;
    }
}
