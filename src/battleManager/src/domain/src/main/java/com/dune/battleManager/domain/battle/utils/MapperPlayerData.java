package com.dune.battleManager.domain.battle.utils;

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

import java.util.ArrayList;

public class MapperPlayerData {

    public static Player mapper(PlayerData playerReq) {
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

    public static PlayerData mapper(Player player) {
        // Crear el DTO final
        PlayerData playerData = new PlayerData();

        // Asignar campos básicos (siempre revisando los ValueObjects)
        playerData.setName(player.getName().getValue());                     // Name -> String
        playerData.setVictoryPoints(player.getVictoryPoints().getValue());   // VictoryPoints -> Integer
        playerData.setDeployedAgent(player.getDeployedAgent().getValue());   // DeployedAgent -> Boolean
        playerData.setAlliance(player.getAlliance().getValue());             // Alliance -> String
        playerData.setBattleStrength(player.getBattleStrength().getValue()); // BattleStrength -> Integer

        // Recursos (Resource -> ResourceRequest)
        if (player.getResources() != null && !player.getResources().isEmpty()) {
            ArrayList<PlayerData.ResourceRequest> resourceList = new ArrayList<>();
            player.getResources().forEach(resource -> {
                PlayerData.ResourceRequest resourceData = new PlayerData.ResourceRequest(
                        resource.getType(),
                        resource.getDescription()
                );
                resourceList.add(resourceData);
            });
            playerData.setResources(resourceList);
        } else {
            playerData.setResources(new ArrayList<>());
        }

        // Cartas de intriga de combate (CombatIntrigueCard -> CombatIntrigueCardRequest)
        if (player.getCombatIntrigueCard() != null && !player.getCombatIntrigueCard().isEmpty()) {
            ArrayList<PlayerData.CombatIntrigueCardRequest> cardList = new ArrayList<>();
            player.getCombatIntrigueCard().forEach(card -> {
                PlayerData.CombatIntrigueCardRequest cardData = new PlayerData.CombatIntrigueCardRequest(
                        card.getName(),
                        card.getEffect(),
                        card.getStack()
                );
                cardList.add(cardData);
            });
            playerData.setCombatIntrigueCard(cardList);
        } else {
            playerData.setCombatIntrigueCard(new ArrayList<>());
        }

        // Líder (Leader -> LeaderRequest)
        if (player.getLeader() != null) {
            Leader leader = player.getLeader();
            PlayerData.LeaderRequest leaderData = new PlayerData.LeaderRequest(
                    leader.getName().getValue(),
                    leader.getHiddenAbility().getName(),
                    leader.getHiddenAbility().getEffect(),
                    leader.getHiddenAbility().getStack(),
                    leader.getHiddenAbility().getCardNameToUnlock(),
                    leader.getHause().getValue(),
                    leader.getDifficultyLevel().getValue(),
                    leader.getPermanentAbility().getName(),
                    leader.getPermanentAbility().getEffect(),
                    leader.getPermanentAbility().getStack(),
                    leader.getBlockHiddenAbility().getValue()
            );
            playerData.setLeader(leaderData);
        }

        // Garrison (Garrison -> GarrisonRequest)
        if (player.getGarrison() != null) {
            Garrison garrison = player.getGarrison();
            PlayerData.GarrisonRequest garrisonData = new PlayerData.GarrisonRequest(
                    garrison.getTotalTroops().getValue(),
                    garrison.getBattleReadyTroops().getValue()
            );
            playerData.setGarrison(garrisonData);
        }

        return playerData;
    }




}
