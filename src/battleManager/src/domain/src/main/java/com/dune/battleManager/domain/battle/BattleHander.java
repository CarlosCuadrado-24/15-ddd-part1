package com.dune.battleManager.domain.battle;

import com.dune.battleManager.domain.battle.events.ConflictWinnerDetermined;
import com.dune.battleManager.domain.battle.events.NewRoundStarted;
import com.dune.battleManager.domain.battle.events.ParticipantsConfirmed;
import com.dune.battleManager.domain.battle.events.PlayersLoaded;
import com.dune.battleManager.domain.battle.events.TerritoryBonusApplied;
import com.dune.battleManager.domain.battle.events.TerritoryCurseApplied;
import com.dune.battleManager.domain.battle.values.Round;
import com.dune.battleManager.domain.player.Player;
import com.dune.battleManager.domain.player.values.BattleStrength;
import com.dune.shared.domain.generic.DomainActionsContainer;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.Comparator;
import java.util.function.Consumer;

public class BattleHander extends DomainActionsContainer {

    public BattleHander(Battle battle){
        add(startNewRound(battle));
        add(confirmParticipants(battle));
        add(loadPlayers(battle));
        add(applyTerritoryBonus(battle));
        add(applyTerritoryCurse(battle));
        add(determineConflictWinner(battle));
    }


    public Consumer<? extends DomainEvent> startNewRound(Battle battle){
        return (NewRoundStarted event) ->{
            battle.setRound(Round.of(battle.getRound().getValue()+1));
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
