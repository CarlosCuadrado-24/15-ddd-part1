package com.dune.battleManager.domain.battle;

import com.dune.battleManager.domain.battle.entities.ConflictCard;
import com.dune.battleManager.domain.battle.entities.Faction;
import com.dune.battleManager.domain.battle.entities.Territory;
import com.dune.battleManager.domain.battle.events.ConflictCardRevealed;
import com.dune.battleManager.domain.battle.events.ConflictWinnerDetermined;
import com.dune.battleManager.domain.battle.events.NewRoundStarted;
import com.dune.battleManager.domain.battle.events.ParticipantsConfirmed;
import com.dune.battleManager.domain.battle.events.RewardsGranted;
import com.dune.battleManager.domain.battle.events.TerritoryBonusApplied;
import com.dune.battleManager.domain.battle.events.TerritoryCurseApplied;
import com.dune.battleManager.domain.battle.values.BattleId;
import com.dune.battleManager.domain.battle.values.Round;
import com.dune.battleManager.domain.battle.values.Rule;
import com.dune.battleManager.domain.player.events.RewardsReceived;
import com.dune.shared.domain.generic.AggregateRoot;

import java.util.ArrayList;

public class Battle extends AggregateRoot<BattleId> {
    private ArrayList<Integer> jugadores;
    private ArrayList<Rule> rules;
    private Round round;
    private Territory territory;
    private ConflictCard conflictCard;
    private Faction faction;

    public Battle() {
        super(new BattleId());
    }

    public Battle(BattleId identity) {
        super(identity);
    }

    public ArrayList<Integer> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Integer> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public ConflictCard getConflictCard() {
        return conflictCard;
    }

    public void setConflictCard(ConflictCard conflictCard) {
        this.conflictCard = conflictCard;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public void RevealConflictCard(){
        apply(new ConflictCardRevealed());
    }

    public void DetermineConflictWinner(){
        apply(new ConflictWinnerDetermined());
    }

    public void StartNewRound(){
        apply(new NewRoundStarted());
    }

    public void ConfirmParticipants(){
        apply(new ParticipantsConfirmed());
    }

    public void GrantRewards(String name, Integer victoryPoints, Integer troops, Integer resources){
        apply(new RewardsGranted(name,victoryPoints,troops,resources));
    }

    public void ApplyTerritoryBonus(){
        apply(new TerritoryBonusApplied());
    }

    public void ApplyTerritoryCurse(){
        apply(new TerritoryCurseApplied());
    }


}
