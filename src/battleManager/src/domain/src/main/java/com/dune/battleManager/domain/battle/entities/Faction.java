package com.dune.battleManager.domain.battle.entities;

import com.dune.battleManager.domain.battle.values.Description;
import com.dune.battleManager.domain.battle.values.FactionId;
import com.dune.battleManager.domain.battle.values.Name;
import com.dune.shared.domain.generic.Entity;

public class Faction extends Entity<FactionId> {
    private Name name;
    private Description description;

    public Faction(FactionId identity, Name name, Description description) {
        super(identity);
        this.name = name;
        this.description = description;
    }

    public Faction(Name name, Description description) {
        super(new FactionId());
        this.name = name;
        this.description = description;
    }

    public Integer increaseTroops(String alianza, Integer troops,Integer rulefactionTroops){
        if(alianza.equals(name.getValue())){
            troops=troops+rulefactionTroops;
        }
        return troops;
    }

    public String modifyResources(String alianza,String recourse, String rulefactionResource){
        if(alianza.equals(name.getValue())){
            return rulefactionResource;
        }
        return recourse;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
