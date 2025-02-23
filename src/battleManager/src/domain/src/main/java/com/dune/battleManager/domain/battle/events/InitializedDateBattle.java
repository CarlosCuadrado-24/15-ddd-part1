package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class InitializedDateBattle extends DomainEvent {
    public InitializedDateBattle() {
        super(EventsEnum.INITIALIZED_GAME.name());
    }
}
