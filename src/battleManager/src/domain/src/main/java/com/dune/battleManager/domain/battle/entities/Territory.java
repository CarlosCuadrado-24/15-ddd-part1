package com.dune.battleManager.domain.battle.entities;

import com.dune.battleManager.domain.battle.values.Banner;
import com.dune.battleManager.domain.battle.values.Bonus;
import com.dune.battleManager.domain.battle.values.Curse;
import com.dune.battleManager.domain.battle.values.Name;
import com.dune.battleManager.domain.battle.values.TerritoryId;
import com.dune.shared.domain.generic.Entity;

public class Territory extends Entity<TerritoryId> {

    private Name name;
    private Banner banner;
    private Bonus bonus;
    private Curse curse;

    public Territory(TerritoryId identity, Name name, Banner banner, Bonus bonus, Curse curse) {
        super(identity);
        this.name = name;
        this.banner = banner;
        this.bonus = bonus;
        this.curse = curse;
    }

    public Territory(Name name, Banner banner, Bonus bonus, Curse curse) {
        super(new TerritoryId());
        this.name = name;
        this.banner = banner;
        this.bonus = bonus;
        this.curse = curse;
    }

    public Integer increaseBattleStrength(Integer battleStrength, String allowed, String resource){
        if(allowed.equals(resource) && bonus.getEffect().equals("Battle Strength")){
            return battleStrength+bonus.getStack();
        }
        return battleStrength;
    }

    public Integer decreaseResources(String resource, Integer cantResource, String forbidden){
        if(resource.equals(forbidden) && curse.getEffect().equals("decrease resources")){
            return cantResource-curse.getStack();
        }
        return cantResource;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public Curse getCurse() {
        return curse;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
    }




}
