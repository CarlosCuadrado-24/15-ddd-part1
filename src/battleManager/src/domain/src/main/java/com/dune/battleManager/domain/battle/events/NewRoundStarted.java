package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class NewRoundStarted extends DomainEvent {
    public NewRoundStarted() {
        super(EventsEnum.NEW_ROUND_STARTED.name());
    }
}
