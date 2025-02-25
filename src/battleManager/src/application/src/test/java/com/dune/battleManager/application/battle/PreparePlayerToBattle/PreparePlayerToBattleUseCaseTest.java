package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import static org.junit.jupiter.api.Assertions.*;

import com.dune.battleManager.application.battle.shared.ports.IEventsRepositoryPort;
import com.dune.battleManager.application.battle.shared.PlayerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

class PreparePlayerToBattleUseCaseTest {

    private IEventsRepositoryPort repository;
    private PreparePlayerToBattleUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
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
