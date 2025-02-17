package com.dune.battleManager.domain.player.entities;

import com.dune.battleManager.domain.player.values.BattleReadyTroops;
import com.dune.battleManager.domain.player.values.CombatIntrigueCard;
import com.dune.battleManager.domain.player.values.GarrisonId;
import com.dune.battleManager.domain.player.values.HiddenAbility;
import com.dune.battleManager.domain.player.values.PermanentAbility;
import com.dune.battleManager.domain.player.values.TotalTroops;
import com.dune.shared.domain.generic.Entity;

public class Garrison extends Entity<GarrisonId> {
    private TotalTroops totalTroops;
    private BattleReadyTroops battleReadyTroops;


    public Garrison(GarrisonId identity, TotalTroops totalTroops, BattleReadyTroops battleReadyTroops) {
        super(identity);
        this.totalTroops = totalTroops;
        this.battleReadyTroops = battleReadyTroops;
    }

    public Garrison(TotalTroops totalTroops, BattleReadyTroops battleReadyTroops) {
        super(new GarrisonId());
        this.totalTroops = totalTroops;
        this.battleReadyTroops = battleReadyTroops;
    }

    public void modifyAvailableTroops(String combatIntrigueCardName, Integer combatIntrigueCardStack, String permanentAbility, Integer permanentAbilityStack){
       Integer troops = battleReadyTroops.getValue();
       if(combatIntrigueCardName.equals("Troops To Battle")  && totalTroops.getValue()>=combatIntrigueCardStack){
           troops=troops+combatIntrigueCardStack;
       }
       if(permanentAbility.equals("Warlord") && totalTroops.getValue()>=permanentAbilityStack){
           troops=troops+permanentAbilityStack;
       }
       this.setBattleReadyTroops(BattleReadyTroops.of(troops));
       this.calculateTotalTroops(troops);
    }

    private void calculateTotalTroops (Integer troops){
        Integer total = totalTroops.getValue();
        total = total - troops;
        this.setTotalTroops(TotalTroops.of(total));
    }

    public TotalTroops getTotalTroops() {
        return totalTroops;
    }

    public void setTotalTroops(TotalTroops totalTroops) {
        this.totalTroops = totalTroops;
    }

    public BattleReadyTroops getBattleReadyTroops() {
        return battleReadyTroops;
    }

    public void setBattleReadyTroops(BattleReadyTroops battleReadyTroops) {
        this.battleReadyTroops = battleReadyTroops;
    }
}
