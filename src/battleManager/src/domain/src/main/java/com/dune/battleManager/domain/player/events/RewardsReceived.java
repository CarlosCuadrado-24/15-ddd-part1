package com.dune.battleManager.domain.player.events;

import com.dune.shared.domain.generic.DomainEvent;

public class RewardsReceived extends DomainEvent {

    private final Integer victoryPoints;
    private final Integer cantResource;
    private final String typeResource;
    private final Integer troops;

    public RewardsReceived(Integer victoryPoints, Integer cantResource, String typeResource, Integer troops) {
        super(EventsEnum.REWARDS_RECEIVED.name());
        this.victoryPoints = victoryPoints;
        this.cantResource = cantResource;
        this.typeResource = typeResource;
        this.troops = troops;
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    public Integer getTroops() {
        return troops;
    }

    public String getTypeResource() {
        return typeResource;
    }

    public Integer getCantResource() {
        return cantResource;
    }
}
