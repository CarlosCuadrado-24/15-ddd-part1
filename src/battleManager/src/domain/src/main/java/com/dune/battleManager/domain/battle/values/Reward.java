package com.dune.battleManager.domain.battle.values;

import com.dune.shared.domain.generic.IValueObject;

public class Reward implements IValueObject {

    private final Integer victoryPoints;
    private final Integer troops;
    private final Integer resources;

    private Reward(Integer victoryPoints, Integer troops, Integer resources) {
        this.victoryPoints = victoryPoints;
        this.troops = troops;
        this.resources = resources;
    }

    public static Reward of (Integer victoryPoints, Integer troops, Integer resources){
        return new Reward(victoryPoints,troops,resources);
    }

    @Override
    public void validate() {
        if(this.victoryPoints == null && this.troops == null && this.resources == null ) {
            throw new IllegalArgumentException("The victoryPoints,troops and resources can't be null");
        }
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
