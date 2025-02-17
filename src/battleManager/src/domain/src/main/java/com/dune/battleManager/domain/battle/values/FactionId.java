package com.dune.battleManager.domain.battle.values;

import com.dune.battleManager.domain.player.values.PlayerId;
import com.dune.shared.domain.generic.Identity;

public class FactionId extends Identity {
    public FactionId() {
        super();
    }

    private FactionId(String value) {
        super(value);
    }

    public static FactionId of(String value){
        return new FactionId(value);
    }
}
