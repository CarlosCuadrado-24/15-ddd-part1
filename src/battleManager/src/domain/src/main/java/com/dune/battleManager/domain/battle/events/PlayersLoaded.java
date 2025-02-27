package com.dune.battleManager.domain.battle.events;

import com.dune.battleManager.domain.player.Player;
import com.dune.shared.domain.generic.DomainEvent;

import java.util.ArrayList;

public class PlayersLoaded extends DomainEvent {
    private ArrayList<Player> players;

    public PlayersLoaded(ArrayList<Player> players) {
        super(EventsEnum.PLAYERS_LOADED.name());
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
