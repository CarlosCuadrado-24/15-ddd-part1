package com.dune.battleManager.application.battle.shared;

import com.dune.battleManager.domain.player.Player;
import com.dune.battleManager.domain.player.entities.Garrison;
import com.dune.battleManager.domain.player.entities.Leader;
import com.dune.battleManager.domain.player.values.Alliance;
import com.dune.battleManager.domain.player.values.BattleReadyTroops;
import com.dune.battleManager.domain.player.values.BattleStrength;
import com.dune.battleManager.domain.player.values.BlockHiddenAbility;
import com.dune.battleManager.domain.player.values.CombatIntrigueCard;
import com.dune.battleManager.domain.player.values.DeployedAgent;
import com.dune.battleManager.domain.player.values.DifficultyLevel;
import com.dune.battleManager.domain.player.values.Hause;
import com.dune.battleManager.domain.player.values.HiddenAbility;
import com.dune.battleManager.domain.player.values.Name;
import com.dune.battleManager.domain.player.values.PermanentAbility;
import com.dune.battleManager.domain.player.values.Resource;
import com.dune.battleManager.domain.player.values.TotalTroops;
import com.dune.battleManager.domain.player.values.VictoryPoints;

public class MapperPlayer {

    public static Player mapper(PlayerRequest playerReq) {
        Player player = new Player();
        player.setName(Name.of(playerReq.getName()));
        player.setVictoryPoints(VictoryPoints.of(playerReq.getVictoryPoints()));
        player.setDeployedAgent(DeployedAgent.of(playerReq.getDeployedAgent()));
        player.setAlliance(Alliance.of(playerReq.getAlliance()));
        player.setBattleStrength(BattleStrength.of(playerReq.getBattleStrength()));

        if (playerReq.getResources() != null) {
            playerReq.getResources().forEach(resourceRequest -> {

                Resource resource = Resource.of(
                        resourceRequest.getType(),
                        resourceRequest.getDescription()
                );
                player.getResources().add(resource);
            });
        }


        if (playerReq.getCombatIntrigueCard() != null) {
            playerReq.getCombatIntrigueCard().forEach(cardRequest -> {

                CombatIntrigueCard card = CombatIntrigueCard.of(
                        cardRequest.getName(),
                        cardRequest.getEffect(),
                        cardRequest.getStack()
                );
                player.getCombatIntrigueCard().add(card);
            });
        }


        if (playerReq.getLeader() != null) {

            Name leaderName = Name.of(playerReq.getLeader().getName());
            HiddenAbility hiddenAbility = HiddenAbility.of(
                    playerReq.getLeader().getHiddenAbilityName(),
                    playerReq.getLeader().getHiddenAbilityResource(),
                    playerReq.getLeader().getHiddenAbilityValue(),
                    playerReq.getLeader().getHiddenAbilityExtra()
            );
            Hause hause = Hause.of(playerReq.getLeader().getHouseRequest());
            DifficultyLevel difficultyLevel = DifficultyLevel.of(playerReq.getLeader().getDifficultyLevel());
            PermanentAbility permanentAbility = PermanentAbility.of(
                    playerReq.getLeader().getPermanentAbilityName(),
                    playerReq.getLeader().getPermanentAbilityResource(),
                    playerReq.getLeader().getPermanentAbilityValue()
            );
            BlockHiddenAbility blockHiddenAbility = BlockHiddenAbility.of(
                    playerReq.getLeader().getBlockHiddenAbility()
            );

            Leader leaderDomain = new Leader(
                    leaderName,
                    hiddenAbility,
                    hause,
                    difficultyLevel,
                    permanentAbility,
                    blockHiddenAbility
            );
            player.setLeader(leaderDomain);
        }


        if (playerReq.getGarrison() != null) {
            TotalTroops totalTroops = TotalTroops.of(playerReq.getGarrison().getTotalTroops());
            BattleReadyTroops battleReadyTroops = BattleReadyTroops.of(playerReq.getGarrison().getBattleReadyTroops());

            Garrison garrisonDomain = new Garrison(totalTroops, battleReadyTroops);

            player.setGarrison(garrisonDomain);
        }
        return player;
    }


}
