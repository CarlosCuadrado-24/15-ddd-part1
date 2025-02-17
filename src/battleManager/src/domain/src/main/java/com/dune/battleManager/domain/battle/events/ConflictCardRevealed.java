package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class ConflictCardRevealed extends DomainEvent {

    public ConflictCardRevealed() {
        super(EventsEnum.CONFLICT_CARD_REVEALED.name());
    }
}
