package com.dune.battleManager.application.battle.determineWinner;

import static org.junit.jupiter.api.Assertions.*;

import com.dune.battleManager.application.battle.shared.IEventsRepository;
import com.dune.battleManager.domain.battle.events.ParticipantsConfirmed;
import com.dune.battleManager.domain.battle.events.PlayersLoaded;
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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DetermineWinnerUseCaseTest {

    private DetermineWinnerUseCase useCase;
    private IEventsRepository repository;
    private ArrayList<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new DetermineWinnerUseCase(repository);

        //player 1
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

        //player 2
        Player player2= new Player();
        player2.setName(Name.of("pablo"));
        player2.setVictoryPoints(VictoryPoints.of(0));
        player2.setBattleStrength(BattleStrength.of(0));
        player2.setAlliance(Alliance.of("Emperador"));
        player2.setDeployedAgent(DeployedAgent.of(true));
        player2.getResources().add(Resource.of("water","water"));
        player2.getCombatIntrigueCard().add(CombatIntrigueCard.of("Troops To Battle","troops",1));

        Name leaderName2 = Name.of("Paul Atreides");
        HiddenAbility hiddenAbility2 = HiddenAbility.of("prueba","Troops",2,"anillo");
        Hause hause2 = Hause.of("Atreides");
        DifficultyLevel difficultyLevel2 = DifficultyLevel.of(3);
        PermanentAbility permanentAbility2 = PermanentAbility.of("Warlord","troops",2);
        BlockHiddenAbility blockHiddenAbility2 = BlockHiddenAbility.of(true);
        Leader leader2 = new Leader(
                leaderName,
                hiddenAbility,
                hause,
                difficultyLevel,
                permanentAbility,
                blockHiddenAbility
        );
        player2.setLeader(leader);

        TotalTroops totalTroops2 = TotalTroops.of(5);
        BattleReadyTroops battleReadyTroops2 = BattleReadyTroops.of(1);
        Garrison garrison2 = new Garrison(totalTroops, battleReadyTroops);
        player2.setGarrison(garrison);

        //add a la lista
        players.add(player);
        players.add(player2);

    }

    @Test
    void determineWinnerSuccess() {

        Mockito.when(repository.findEventsByAggregateId(anyString()))
                .thenReturn(Flux.just(
                        new PlayersLoaded(players),
                        new ParticipantsConfirmed()
                ));


        DetermineWinnerRequest request = new DetermineWinnerRequest("aggregatexyz");


        StepVerifier
                .create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals("carlos", response.getPlayerName());
                    assertNotNull(response.getRewardConflict());
                    assertEquals(1, response.getRewardConflict().getVictoryPoints());
                    assertEquals(2, response.getRewardConflict().getTroops());
                    assertEquals(3, response.getRewardConflict().getResources());
                })
                .verifyComplete();


        verify(repository).findEventsByAggregateId(anyString());
    }
}
