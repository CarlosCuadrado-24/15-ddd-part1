package com.dune.battleManager.domain.battle.events;

import com.dune.shared.domain.generic.DomainEvent;

public class TerritoryBonusApplied extends DomainEvent {

    public TerritoryBonusApplied() {
        super(EventsEnum.TERRITORY_BONUS_APPLIED.name());
    }
}
