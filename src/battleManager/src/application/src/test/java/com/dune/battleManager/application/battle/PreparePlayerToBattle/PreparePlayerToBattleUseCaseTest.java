package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import static org.junit.jupiter.api.Assertions.*;

import com.dune.battleManager.application.battle.shared.IEventsRepository;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PreparePlayerToBattleUseCaseTest {

    private IEventsRepository repository;
    private PreparePlayerToBattleUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new PreparePlayerToBattleUseCase(repository);
    }

    @Test
    void testPreparePlayerToBattleSuccess() {

        Player player = new Player();
        player.setName(Name.of("carlos"));
        player.setVictoryPoints(VictoryPoints.of(1));
        player.setBattleStrength(BattleStrength.of(0));
        player.setAlliance(Alliance.of("Emperador"));
        player.setDeployedAgent(DeployedAgent.of(true));
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


        PreparePlayerToBattleRequest request = new PreparePlayerToBattleRequest("xd",0, player);


        StepVerifier.create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertTrue(response.getAgentToBattle());
                    assertEquals(5, response.getTroopsToBattle());
                    assertEquals(10, response.getBattleStrength());
                })
                .verifyComplete();
    }
}
