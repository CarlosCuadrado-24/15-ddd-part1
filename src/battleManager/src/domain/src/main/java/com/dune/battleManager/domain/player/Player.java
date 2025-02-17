package com.dune.battleManager.domain.player;

import com.dune.battleManager.domain.player.entities.Garrison;
import com.dune.battleManager.domain.player.entities.Leader;
import com.dune.battleManager.domain.player.events.AgentDeployed;
import com.dune.battleManager.domain.player.events.BattleStrengthCalculated;
import com.dune.battleManager.domain.player.events.LeaderHiddenAbilityUsed;
import com.dune.battleManager.domain.player.events.RewardsReceived;
import com.dune.battleManager.domain.player.events.TroopsDeployedToBattle;
import com.dune.battleManager.domain.player.values.Alliance;
import com.dune.battleManager.domain.player.values.BattleStrength;
import com.dune.battleManager.domain.player.values.CombatIntrigueCard;
import com.dune.battleManager.domain.player.values.DeployedAgent;
import com.dune.battleManager.domain.player.values.Name;
import com.dune.battleManager.domain.player.values.PlayerId;
import com.dune.battleManager.domain.player.values.Resource;
import com.dune.battleManager.domain.player.values.VictoryPoints;
import com.dune.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;

public class Player extends AggregateRoot<PlayerId> {
    private Name name;
    private VictoryPoints victoryPoints;
    private ArrayList<Resource> resources;
    private ArrayList<CombatIntrigueCard> combatIntrigueCard;
    private DeployedAgent deployedAgent;
    private Alliance alliance;
    private BattleStrength battleStrength;
    private Leader leader;
    private Garrison garrison;

    public Player() {
        super(new PlayerId());
    }

    private Player(PlayerId identity) {
        super(identity);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public VictoryPoints getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(VictoryPoints victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public ArrayList<CombatIntrigueCard> getCombatIntrigueCard() {
        return combatIntrigueCard;
    }

    public void setCombatIntrigueCard(ArrayList<CombatIntrigueCard> combatIntrigueCard) {
        this.combatIntrigueCard = combatIntrigueCard;
    }

    public DeployedAgent getDeployedAgent() {
        return deployedAgent;
    }

    public void setDeployedAgent(DeployedAgent deployedAgent) {
        this.deployedAgent = deployedAgent;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public BattleStrength getBattleStrength() {
        return battleStrength;
    }

    public void setBattleStrength(BattleStrength battleStrength) {
        this.battleStrength = battleStrength;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public Garrison getGarrison() {
        return garrison;
    }

    public void setGarrison(Garrison garrison) {
        this.garrison = garrison;
    }

    public void deployTroopsToBattle(String combatIntrigueCardName, Integer combatIntrigueCardStack, String permanentAbility, Integer permanentAbilityStack){
        apply(new TroopsDeployedToBattle(combatIntrigueCardName,combatIntrigueCardStack,permanentAbility,permanentAbilityStack));
    }

    public void calculateBattleStrength(Integer battleStrength, Integer battleReadyTroops, String effectIntrigueCard, Integer stackIntrigueCard, String effectPermanentAbilityLeader, Integer stackPermanentAbilityLeader){
        apply(new BattleStrengthCalculated(battleStrength,battleReadyTroops, effectIntrigueCard,stackIntrigueCard,effectPermanentAbilityLeader,stackPermanentAbilityLeader));
    }

    public void deployAgent(){
        apply(new AgentDeployed());
    }

    public void useLeaderHiddenAbility(String combatIntrigueCard){
        apply(new LeaderHiddenAbilityUsed(combatIntrigueCard));
    }

    public void receiveRewards(Integer victoryPoints, Integer cantResource, String typeResource, Integer troops){
        apply(new RewardsReceived(victoryPoints,cantResource,typeResource,troops));
    }


}
