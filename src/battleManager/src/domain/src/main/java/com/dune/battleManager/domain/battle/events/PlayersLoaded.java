package com.dune.battleManager.domain.battle.events;

import com.dune.battleManager.domain.battle.utils.PlayerData;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.ArrayList;

public class PlayersLoaded extends DomainEvent {
//    private ArrayList<Player> players;
//
//    public PlayersLoaded(ArrayList<Player> players) {
//        super(EventsEnum.PLAYERS_LOADED.name());
//        this.players = players;
//    }
//
//    public PlayersLoaded() {
//        super(EventsEnum.PLAYERS_LOADED.name());
//    }
//
//    public ArrayList<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(ArrayList<Player> players) {
//        this.players = players;
//    }

    private ArrayList<PlayerData> players;

    public PlayersLoaded(ArrayList<PlayerData> players) {
        super(EventsEnum.PLAYERS_LOADED.name());
        this.players = players;
    }

    public ArrayList<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerData> players) {
        this.players = players;
    }
}
