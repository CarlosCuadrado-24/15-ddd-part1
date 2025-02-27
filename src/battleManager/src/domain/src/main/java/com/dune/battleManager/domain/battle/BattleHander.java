package com.dune.battleManager.domain.battle;

import com.dune.battleManager.domain.battle.entities.ConflictCard;
import com.dune.battleManager.domain.battle.entities.Faction;
import com.dune.battleManager.domain.battle.entities.Territory;
import com.dune.battleManager.domain.battle.events.ConflictWinnerDetermined;
import com.dune.battleManager.domain.battle.events.InitializedDateBattle;
import com.dune.battleManager.domain.battle.events.NewRoundStarted;
import com.dune.battleManager.domain.battle.events.ParticipantsConfirmed;
import com.dune.battleManager.domain.battle.events.PlayersLoaded;
import com.dune.battleManager.domain.battle.events.TerritoryBonusApplied;
import com.dune.battleManager.domain.battle.events.TerritoryCurseApplied;
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
import com.dune.shared.domain.generic.DomainActionsContainer;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class BattleHander extends DomainActionsContainer {

    public BattleHander(Battle battle){
        add(startNewRound(battle));
        add(confirmParticipants(battle));
        add(loadPlayers(battle));
        add(applyTerritoryBonus(battle));
        add(applyTerritoryCurse(battle));
        add(determineConflictWinner(battle));
        add(initDataBattle(battle));
    }


    public Consumer<? extends DomainEvent> startNewRound(Battle battle){
        return (NewRoundStarted event) ->{
            battle.setRound(Round.of(battle.getRound().getValue()+1));
        };
    }

    public Consumer<? extends DomainEvent> initDataBattle(Battle battle){
        return (InitializedDateBattle event) ->{
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

            battle.setFaction(new Faction(Name.of("Emperator"), Description.of("Emperator")));

            Name territoryName = Name.of("Arrakis");
            Banner banner = Banner.of("Banner");
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

        };
    }

    public Consumer<? extends DomainEvent> confirmParticipants(Battle battle){
        return (ParticipantsConfirmed event) ->{
            for(int i=0; i<battle.getPlayers().size(); i++){
                if(!battle.getPlayers().get(i).getDeployedAgent().getValue()){
                    battle.getPlayers().remove(i);
                }
            }
        };
    }


    public Consumer<? extends DomainEvent> loadPlayers(Battle battle){
        return (PlayersLoaded event) ->{
            battle.setPlayers(event.getPlayers());
            System.out.println(battle.getPlayers().get(0).getName().getValue());
        };
    }

    public Consumer<? extends DomainEvent> applyTerritoryBonus(Battle battle) {
        return (TerritoryBonusApplied event) -> {
            for (Player player : battle.getPlayers()) {
                Integer battleStrength = player.getBattleStrength().getValue();
                for (int i = 0; i < battle.getRules().size(); i++) {
                    battleStrength = battle.getTerritory()
                            .increaseBattleStrength(battleStrength,
                                    battle.getRules().get(i).getAllowed(),
                                    player.getResources().get(0).getType());
                }
                player.setBattleStrength(BattleStrength.of(battleStrength));
            }
        };
    }


    public Consumer<? extends DomainEvent> applyTerritoryCurse(Battle battle){
        return (TerritoryCurseApplied event) ->{
            for (Player player : battle.getPlayers()) {
                Integer cantresources = player.getResources().size();
                for(int i=0; i<battle.getRules().size(); i++){
                    cantresources = battle.getTerritory().decreaseResources(player.getResources().get(0).getType(),cantresources,battle.getRules().get(0).getForbidden());
                }
                for(int x=0; x<cantresources; x++){
                    player.getResources().remove(x);
                }
            }
        };
    }

    public Consumer<? extends DomainEvent> determineConflictWinner(Battle battle){
        return (ConflictWinnerDetermined event) ->{
            Player strongestPlayer = null;
            int maxStrength = Integer.MIN_VALUE;

            for (Player player : battle.getPlayers()) {
                if (player.getBattleStrength().getValue() > maxStrength) {
                    maxStrength = player.getBattleStrength().getValue();
                    strongestPlayer = player;
                }
            }
            if (strongestPlayer != null) {
                battle.setWinner(strongestPlayer);
            }
        };
    }












}
