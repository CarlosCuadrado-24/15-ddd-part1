package com.dune.battleManager.domain.battle.values;

import com.dune.battleManager.domain.player.values.PlayerId;
import com.dune.shared.domain.generic.Identity;

public class TerritoryId extends Identity {
    public TerritoryId() {
        super();
    }

    private TerritoryId(String value) {
        super(value);
    }

    public static TerritoryId of(String value){
        return new TerritoryId(value);
    }
}
