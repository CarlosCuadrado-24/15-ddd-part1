package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class TerritoryCurseApplied extends DomainEvent {

    public TerritoryCurseApplied() {
        super(EventsEnum.TERRITORY_CURSE_APPLIED.name());
    }
}
