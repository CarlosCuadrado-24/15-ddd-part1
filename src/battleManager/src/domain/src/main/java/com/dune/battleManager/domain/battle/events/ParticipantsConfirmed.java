package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class ParticipantsConfirmed extends DomainEvent {
    public ParticipantsConfirmed() {
        super(EventsEnum.PARTICIPANTS_CONFIRMED.name());
    }
}
