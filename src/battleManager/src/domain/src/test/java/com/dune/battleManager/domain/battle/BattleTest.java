package com.dune.battleManager.domain.battle;

import com.dune.battleManager.domain.battle.entities.ConflictCard;
import com.dune.battleManager.domain.battle.entities.Faction;
import com.dune.battleManager.domain.battle.entities.Territory;
import com.dune.battleManager.domain.battle.events.NewRoundStarted;
import com.dune.battleManager.domain.battle.values.Banner;
import com.dune.battleManager.domain.battle.values.Bonus;
import com.dune.battleManager.domain.battle.values.Curse;
import com.dune.battleManager.domain.battle.values.Description;
import com.dune.battleManager.domain.battle.values.IntensityLevel;
import com.dune.battleManager.domain.battle.values.Name;
import com.dune.battleManager.domain.battle.values.Reward;
import com.dune.battleManager.domain.battle.values.Round;
import com.dune.battleManager.domain.battle.values.Rule;
import com.dune.battleManager.domain.player.Player;
import com.dune.battleManager.domain.player.values.BattleStrength;
import com.dune.battleManager.domain.player.values.DeployedAgent;
import com.dune.battleManager.domain.player.values.Resource;
import com.dune.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    private Battle battle;

    @BeforeEach
    void setUp() {
        battle = new Battle();
        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(Rule.of(
                "ResourceLimit",
                "water",
                "Steal",
                "Players can trade resources but cannot steal from others.",
                0,
                "Water"
        ));

        rules.add(Rule.of(
                "TroopDeployment",
                "Deploy",
                "Retreat",
                "Players can deploy troops to the battlefield but cannot retreat once deployed.",
                5,
                "Spice"
        ));

        battle.setRules(rules);
        battle.setRound(Round.of(1));
        battle.setFaction(new Faction(Name.of("prueba"), Description.of("prueba")));

        Name territoryName = Name.of("Arrakis");
        Banner banner = Banner.of("prueba");
        Bonus territoryBonus = Bonus.of("ArrakisBonus","Battle Strength",2);
        Curse territoryCurse = Curse.of("Sandstorm","decrease Resources",1);

        Territory territory = new Territory(
                territoryName,
                banner,
                territoryBonus,
                territoryCurse
        );

        battle.setTerritory(territory);

        Name conflictCardName = Name.of("FirstConflict");
        IntensityLevel intensityLevel = IntensityLevel.of(3);
        Reward reward = Reward.of(1, 2, 3);
        ConflictCard conflictCard = new ConflictCard(
                conflictCardName,
                intensityLevel,
                reward
        );

        battle.setConflictCard(conflictCard);
    }

    @Test
    void createBattleSuccesTest(){
        assertEquals(1,battle.getRound().getValue());
        assertEquals("ResourceLimit",battle.getRules().get(0).getName());
        assertEquals("FirstConflict",battle.getConflictCard().getName().getValue());
        assertEquals("prueba",battle.getFaction().getName().getValue());
    }

    @Test
    void useFromTest(){
        List<DomainEvent> events = List.of(new NewRoundStarted());
        Battle battle1 = Battle.from("1",events);
        assertEquals("1",battle1.getIdentity().getValue());
    }

    @Test
    void startNewRoundTest(){
       battle.startNewRound();
       assertEquals(2,battle.getRound().getValue());
    }

    @Test
    void loadPlayersTest(){
       Player player1 = new Player();
       player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
       Player player2 = new Player();
       player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));

       ArrayList<Player> players = new ArrayList<>();
       players.add(player1);
       players.add(player2);

        battle.loadPlayers(players);
        assertEquals("carlos",battle.getPlayers().get(0).getName().getValue());
    }

    @Test
    void confirmParticipantsTest(){
        Player player1 = new Player();
        player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
        player1.setDeployedAgent(DeployedAgent.of(true));
        Player player2 = new Player();
        player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));
        player1.setDeployedAgent(DeployedAgent.of(false));
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        battle.loadPlayers(players);
        battle.confirmParticipants();
        assertEquals(1,battle.getPlayers().size());
    }
    @Test
    void confirmParticipantsNoRemovalTest() {
        Player player1 = new Player();
        player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
        player1.setDeployedAgent(DeployedAgent.of(true));

        Player player2 = new Player();
        player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));
        player2.setDeployedAgent(DeployedAgent.of(true));

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        battle.loadPlayers(players);
        battle.confirmParticipants();

        assertEquals(2, battle.getPlayers().size());
        assertEquals("carlos", battle.getPlayers().get(0).getName().getValue());
        assertEquals("juan", battle.getPlayers().get(1).getName().getValue());
    }

    @Test
    void determineConflictWinnerTest(){
        Player player1 = new Player();
        player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
        player1.setBattleStrength(BattleStrength.of(4));
        Player player2 = new Player();
        player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));
        player2.setBattleStrength(BattleStrength.of(2));
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        battle.loadPlayers(players);
        battle.determineConflictWinner();
        assertEquals("carlos",battle.getWinner().getName().getValue());
    }

    @Test
    void determineConflictWinnerNoPlayersTest() {
        ArrayList<Player> players = new ArrayList<>();
        battle.loadPlayers(players);
        battle.determineConflictWinner();
        assertNull(battle.getWinner());
    }


    @Test
    void applyTerritoryBonus(){
        Player player1 = new Player();
        player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
        player1.setBattleStrength(BattleStrength.of(4));
        player1.getResources().add(Resource.of("water","water"));
        Player player2 = new Player();
        player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));
        player2.setBattleStrength(BattleStrength.of(2));
        player2.getResources().add(Resource.of("Steal","Steal"));
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        battle.loadPlayers(players);
        battle.applyTerritoryBonus();
        assertEquals(6,battle.getPlayers().get(0).getBattleStrength().getValue());
    }

    @Test
    void applyTerritoryCurse(){
        Player player1 = new Player();
        player1.setName(com.dune.battleManager.domain.player.values.Name.of("carlos"));
        player1.setBattleStrength(BattleStrength.of(4));
        player1.getResources().add(Resource.of("water","water"));
        Player player2 = new Player();
        player2.setName(com.dune.battleManager.domain.player.values.Name.of("juan"));
        player2.setBattleStrength(BattleStrength.of(2));
        player2.getResources().add(Resource.of("Steal","Steal"));
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        battle.loadPlayers(players);
        battle.applyTerritoryCurse();
        assertEquals(0,battle.getPlayers().get(1).getResources().size());
    }

    @Test
    void initDataTest(){
        Battle battle2 = new Battle();
//        battle2.initGame();
        assertEquals("Arrakis", battle2.getTerritory().getName().getValue());
    }











}