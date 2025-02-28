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
import com.dune.battleManager.domain.battle.utils.PlayerData;
import com.dune.battleManager.domain.battle.values.BattleId;
import com.dune.battleManager.domain.battle.values.Round;
import com.dune.battleManager.domain.battle.values.Rule;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.domain.generic.AggregateRoot;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Battle extends AggregateRoot<BattleId> {
    private ArrayList<Player> players;
    private ArrayList<Rule> rules;
    private Round round;
    private Territory territory;
    private ConflictCard conflictCard;
    private Faction faction;
    private Player winner;

    public Battle() {
        super(new BattleId());
        this.players = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.round = Round.of(1);
        subscribe(new BattleHander(this));
        apply(new InitializedDateBattle());
    }

    public Battle(BattleId identity) {
        super(identity);
        this.players = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.round = Round.of(1);
        subscribe(new BattleHander(this));
//        apply(new InitializedDateBattle());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    public void determineConflictWinner(){
        apply(new ConflictWinnerDetermined());
    }

    public void startNewRound(){
        apply(new NewRoundStarted());
    }

    public void confirmParticipants(){
        apply(new ParticipantsConfirmed());
    }

    public void applyTerritoryBonus(){
        apply(new TerritoryBonusApplied());
    }

    public void applyTerritoryCurse(){
        apply(new TerritoryCurseApplied());
    }

    public void loadPlayers(ArrayList<PlayerData> players){
        apply(new PlayersLoaded(players));
//        System.out.println("XDDDDDDDDDDDDDDDD");
    }

//    public void loadPlayers(Player player){
//        apply(new PlayersLoaded(player));
//    }

//    public void initGame(){
//        apply(new InitializedDateBattle());
//    }

    public static Battle from(final String identity, final List<DomainEvent> events) {
        Battle battle = new Battle(BattleId.of(identity));

        events.forEach(battle::apply);
        return battle;
    }


}
