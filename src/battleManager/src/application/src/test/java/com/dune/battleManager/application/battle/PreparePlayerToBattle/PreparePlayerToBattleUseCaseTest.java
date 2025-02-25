package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import static org.junit.jupiter.api.Assertions.*;

import com.dune.battleManager.application.battle.shared.IEventsRepository;
import com.dune.battleManager.application.battle.shared.PlayerRequest;
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

        PlayerRequest player = new PlayerRequest();
        player.setName("carlos");
        player.setVictoryPoints(1);
        player.setBattleStrength(0);
        player.setAlliance("Emperador");
        player.setDeployedAgent(true);
        player.getResources().add(new PlayerRequest.ResourceRequest("water", "water"));
        player.getCombatIntrigueCard().add(new PlayerRequest.CombatIntrigueCardRequest("Troops To Battle","troops",2));

// Crear LeaderRequest con valores primitivos
        PlayerRequest.LeaderRequest leader = new PlayerRequest.LeaderRequest(
                "Paul Atreides", // name
                "prueba", // hiddenAbilityName
                "Troops", // hiddenAbilityResource
                1, // hiddenAbilityValue
                "anillo", // hiddenAbilityExtra
                "Atreides", // houseRequest
                3, // difficultyLevel
                "Warlord", // permanentAbilityName
                "troops", // permanentAbilityResource
                1, // permanentAbilityValue
                true // blockHiddenAbility
        );
        player.setLeader(leader);

// Crear GarrisonRequest con valores primitivos
        PlayerRequest.GarrisonRequest garrison = new PlayerRequest.GarrisonRequest(
                10, // totalTroops
                2 // battleReadyTroops
        );
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
