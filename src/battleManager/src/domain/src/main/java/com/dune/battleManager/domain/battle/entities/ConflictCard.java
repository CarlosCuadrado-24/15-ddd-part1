package com.dune.battleManager.domain.battle.entities;

import com.dune.battleManager.domain.battle.values.ConflictCardId;
import com.dune.battleManager.domain.battle.values.IntensityLevel;
import com.dune.battleManager.domain.battle.values.Name;
import com.dune.battleManager.domain.battle.values.Reward;
import com.dune.shared.domain.generic.Entity;

public class ConflictCard extends Entity<ConflictCardId>{

    private Name name;
    private IntensityLevel intensityLevel;
    private Reward reward;


    public ConflictCard(ConflictCardId identity, Name name, IntensityLevel intensityLevel, Reward reward) {
        super(identity);
        this.name = name;
        this.intensityLevel = intensityLevel;
        this.reward = reward;
    }

    public ConflictCard(Name name, IntensityLevel intensityLevel, Reward reward) {
        super(new ConflictCardId());
        this.name = name;
        this.intensityLevel = intensityLevel;
        this.reward = reward;
    }

    public String Details(){
        return "Name:"+this.name.getValue()+"\n"+
                "Intensity level:"+this.name.getValue()+"\n"+
                "------------------------------------------"+"\n"+
                "Reward"+"\n"+
                "Resources:"+this.reward.getResources()+"\n"+
                "Victory Points:"+this.reward.getVictoryPoints()+"\n"+
                "Troops:"+this.reward.getTroops();
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public IntensityLevel getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(IntensityLevel intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
