package com.dune.battleManager.domain.battle.values;

import com.dune.shared.domain.generic.IValueObject;

public class Rule implements IValueObject {

    private final String name;
    private final String allowed;
    private final String forbidden;
    private final String description;
    private final Integer factionTroops;
    private final String factionResource;

    private Rule(String name, String allowed, String forbidden, String description, Integer factionTroops, String factionResource) {
        this.name = name;
        this.allowed = allowed;
        this.forbidden = forbidden;
        this.description = description;
        this.factionTroops = factionTroops;
        this.factionResource = factionResource;
    }

    public static Rule of(String name, String allowed, String forbidden,String description,Integer factionTroops, String factionResource){
        return new Rule(name, allowed, forbidden,description,factionTroops, factionResource);
    }

    @Override
    public void validate() {
        if(!this.name.matches("^[a-zA-Z]+$") && !this.allowed.matches("^[a-zA-Z]+$") && !this.forbidden.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("The name or allowed or forbidden can't have special characters and numbers");
        }
    }

    public String getForbidden() {
        return forbidden;
    }

    public String getAllowed() {
        return allowed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFactionTroops() {
        return factionTroops;
    }

    public String getFactionResource() {
        return factionResource;
    }
}
