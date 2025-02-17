package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class ConflictWinnerDetermined extends DomainEvent {
    public ConflictWinnerDetermined() {
        super(EventsEnum.CONFLICT_WINNER_DETERMINED.name());
    }
}
