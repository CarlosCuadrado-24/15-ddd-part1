package com.dune.battleManager.domain.player;

import com.dune.battleManager.domain.player.events.AgentDeployed;
import com.dune.battleManager.domain.player.events.BattleStrengthCalculated;
import com.dune.battleManager.domain.player.events.LeaderHiddenAbilityUsed;
import com.dune.battleManager.domain.player.events.RewardsReceived;
import com.dune.battleManager.domain.player.events.TroopsDeployedToBattle;
import com.dune.battleManager.domain.player.values.BattleReadyTroops;
import com.dune.battleManager.domain.player.values.BattleStrength;
import com.dune.battleManager.domain.player.values.BlockHiddenAbility;
import com.dune.battleManager.domain.player.values.DeployedAgent;
import com.dune.battleManager.domain.player.values.Resource;
import com.dune.battleManager.domain.player.values.TotalTroops;
import com.dune.battleManager.domain.player.values.VictoryPoints;
import com.dune.shared.domain.generic.DomainActionsContainer;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class PlayerHandler extends DomainActionsContainer {

    public PlayerHandler(Player player){
        add(deployTroopsToBattle(player));
        add(deployAgentToBattle(player));
        add(useLeaderHiddenAbility(player));
        add(receiveRewards(player));
        add(calculateBattleStrength(player));
    }

    public Consumer<? extends DomainEvent> deployTroopsToBattle(Player player) {
        return (TroopsDeployedToBattle event) -> {
            player.getGarrison().modifyAvailableTroops(
                    player.getCombatIntrigueCard().get(event.getRound()).getName(),
                    player.getCombatIntrigueCard().get(event.getRound()).getStack(),
                    player.getLeader().getPermanentAbility().getName(),
                    player.getLeader().getPermanentAbility().getStack()
            );
        };
    }

    public Consumer<? extends DomainEvent> deployAgentToBattle(Player player){
        return (AgentDeployed event) ->{
            player.setDeployedAgent(DeployedAgent.of(true));
        };
    }

    public Consumer<? extends DomainEvent> useLeaderHiddenAbility(Player player){
        return (LeaderHiddenAbilityUsed event) ->{
            if(!player.getLeader().getBlockHiddenAbility().getValue()){
                if (player.getLeader().getHiddenAbility().getEffect().equals("Battle Strength")){
                    Integer battleStrength = player.getBattleStrength().getValue() + player.getLeader().getHiddenAbility().getStack();
                    player.setBattleStrength(BattleStrength.of(battleStrength));
                    player.getLeader().setBlockHiddenAbility(BlockHiddenAbility.of(true));
                }else if (player.getLeader().getHiddenAbility().getEffect().equals("Troops")){
                    Integer troops = player.getGarrison().getBattleReadyTroops().getValue() + player.getLeader().getHiddenAbility().getStack();
                    player.getGarrison().setBattleReadyTroops(BattleReadyTroops.of(troops));
                    player.getLeader().setBlockHiddenAbility(BlockHiddenAbility.of(true));
                }
            }
        };
    }

    public Consumer<? extends DomainEvent> receiveRewards(Player player){
        return (RewardsReceived event) ->{
            player.setVictoryPoints(VictoryPoints.of(event.getVictoryPoints()));
            for(int i=0; i<event.getCantResource(); i++){
                player.getResources().add(Resource.of(event.getTypeResource(),event.getDescriptionResource()));
            }
            player.getGarrison().setTotalTroops(TotalTroops.of(event.getTroops()));
        };
    }

    public Consumer<? extends DomainEvent> calculateBattleStrength(Player player){
        return (BattleStrengthCalculated event) ->{
            Integer battleStrength = player.getBattleStrength().getValue() + (player.getGarrison().getBattleReadyTroops().getValue() * 2);
            player.setBattleStrength(BattleStrength.of(battleStrength));
        };
    }

}
