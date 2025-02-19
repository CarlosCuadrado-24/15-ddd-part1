package com.dune.battleManager.domain.player;

import com.dune.battleManager.domain.player.entities.Garrison;
import com.dune.battleManager.domain.player.entities.Leader;
import com.dune.battleManager.domain.player.events.AgentDeployed;
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
import com.dune.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setName(Name.of("carlos"));
        player.setVictoryPoints(VictoryPoints.of(1));
        player.setBattleStrength(BattleStrength.of(0));
        player.setAlliance(Alliance.of("Emperador"));
        player.setDeployedAgent(DeployedAgent.of(false));
        player.getResources().add(Resource.of("water","water"));
        player.getCombatIntrigueCard().add(CombatIntrigueCard.of("Troops To Battle","troops",2));

        Name leaderName = Name.of("Paul Atreides");
        HiddenAbility hiddenAbility = HiddenAbility.of("prueba","Troops",1,"anillo");
        Hause hause = Hause.of("Atreides");
        DifficultyLevel difficultyLevel = DifficultyLevel.of(3);
        PermanentAbility permanentAbility = PermanentAbility.of("Warlord","troops",1);
        BlockHiddenAbility blockHiddenAbility = BlockHiddenAbility.of(true);
        Leader leader = new Leader(
                leaderName,
                hiddenAbility,
                hause,
                difficultyLevel,
                permanentAbility,
                blockHiddenAbility
        );
        player.setLeader(leader);

        TotalTroops totalTroops = TotalTroops.of(10);
        BattleReadyTroops battleReadyTroops = BattleReadyTroops.of(2);
        Garrison garrison = new Garrison(totalTroops, battleReadyTroops);
        player.setGarrison(garrison);
    }

    @Test
    void createPlayerSuccesTest(){
        assertEquals("carlos",player.getName().getValue());
        assertEquals(1,player.getVictoryPoints().getValue());
        assertEquals(0,player.getBattleStrength().getValue());
        assertEquals("Emperador",player.getAlliance().getValue());
        assertEquals("Paul Atreides",player.getLeader().getName().getValue());
        assertEquals(3,player.getLeader().getDifficultyLevel().getValue());
        assertEquals(10,player.getGarrison().getTotalTroops().getValue());
        assertEquals(2,player.getGarrison().getBattleReadyTroops().getValue());
    }

    @Test
    void deployTroopsToBattleTest(){
        player.deployTroopsToBattle(0);
        assertEquals(5,player.getGarrison().getBattleReadyTroops().getValue());
        assertEquals(5,player.getGarrison().getTotalTroops().getValue());
    }

    @Test
    void calculateBattleStrengthTest(){
        player.deployTroopsToBattle(0);
        player.calculateBattleStrength(0);
        assertEquals(10,player.getBattleStrength().getValue());
    }

    @Test
    void deployAgentToBattleTest(){
        player.deployAgentToBattle();
        assertEquals(true,player.getDeployedAgent().getValue());
    }

    @Test
    void receiveRewardsTest(){
        player.receiveRewards(1,3,"water","water",5);
        assertEquals(2,player.getVictoryPoints().getValue());
        for (int i=0; i<player.getResources().size(); i++){
            assertEquals("water",player.getResources().get(i).getType());
        }
        assertEquals(15,player.getGarrison().getTotalTroops().getValue());
    }

    @Test
    void useLeaderHiddenAbilitySuccesTest(){
        HiddenAbility hiddenAbility = HiddenAbility.of("prueba","Troops",2,"anillo");
        BlockHiddenAbility blockHiddenAbility = BlockHiddenAbility.of(false);
        player.getLeader().setHiddenAbility(hiddenAbility);
        player.getLeader().setBlockHiddenAbility(blockHiddenAbility);
        player.useLeaderHiddenAbility(0);
        assertEquals(4,player.getGarrison().getBattleReadyTroops().getValue());

        hiddenAbility = HiddenAbility.of("prueba","Battle Strength",2,"anillo");
        blockHiddenAbility = BlockHiddenAbility.of(false);
        player.getLeader().setHiddenAbility(hiddenAbility);
        player.getLeader().setBlockHiddenAbility(blockHiddenAbility);
        player.useLeaderHiddenAbility(0);
        assertEquals("Battle Strength",player.getLeader().getHiddenAbility().getEffect() );
        assertEquals(2,player.getBattleStrength().getValue());
    }

    @Test
    void useLeaderHiddenAbilityFailedTest(){
        HiddenAbility hiddenAbility = HiddenAbility.of("prueba","asdasd",2,"anillo");
        BlockHiddenAbility blockHiddenAbility = BlockHiddenAbility.of(false);
        player.getLeader().setHiddenAbility(hiddenAbility);
        player.getLeader().setBlockHiddenAbility(blockHiddenAbility);
        player.useLeaderHiddenAbility(0);
        assertEquals(2,player.getGarrison().getBattleReadyTroops().getValue());

        hiddenAbility = HiddenAbility.of("prueba","asdasd",2,"anillo");
        blockHiddenAbility = BlockHiddenAbility.of(true);
        player.getLeader().setHiddenAbility(hiddenAbility);
        player.getLeader().setBlockHiddenAbility(blockHiddenAbility);
        player.useLeaderHiddenAbility(0);
        assertEquals(2,player.getGarrison().getBattleReadyTroops().getValue());



    }

    @Test
    void useFromTest(){
        List<DomainEvent> events = List.of(new AgentDeployed());

        Player player1 = Player.from("1",events);

        assertEquals("1",player1.getIdentity().getValue());

    }










}