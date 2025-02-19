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
import com.dune.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

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

    //region constructores
    public Player() {
        super(new PlayerId());
        this.resources = new ArrayList<>();
        this.combatIntrigueCard = new ArrayList<>();
        subscribe(new PlayerHandler(this));
    }

    private Player(PlayerId identity) {
        super(identity);
        subscribe(new PlayerHandler(this));
    }
    //endregion

    //region getters and setters
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

    public ArrayList<CombatIntrigueCard> getCombatIntrigueCard() {
        return combatIntrigueCard;
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
    //endregion

    //region eventos de dominio
    public void deployTroopsToBattle(Integer round){
        apply(new TroopsDeployedToBattle(round));
    }

    public void calculateBattleStrength(Integer round){
        apply(new BattleStrengthCalculated(round));
    }

    public void deployAgentToBattle(){
        apply(new AgentDeployed());
    }

    public void useLeaderHiddenAbility(Integer round){
        apply(new LeaderHiddenAbilityUsed(round));
    }

    public void receiveRewards(Integer victoryPoints, Integer cantResource, String typeResource,String descriptionResource, Integer troops){
        apply(new RewardsReceived(victoryPoints,cantResource,typeResource,descriptionResource,troops));
    }
    //endregion

    public static Player from(final String identity, final List<DomainEvent> events) {
        Player player = new Player(PlayerId.of(identity));

        events.forEach(player::apply);
        return player;
    }


}
