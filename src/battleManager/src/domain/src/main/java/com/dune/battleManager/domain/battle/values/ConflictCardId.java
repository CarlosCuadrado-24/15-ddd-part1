package com.dune.battleManager.domain.battle.values;

import com.dune.battleManager.domain.player.values.PlayerId;
import com.dune.shared.domain.generic.Identity;

public class ConflictCardId extends Identity {
    public ConflictCardId() {
        super();
    }

    private ConflictCardId(String value) {
        super(value);
    }

    public static ConflictCardId of(String value){
        return new ConflictCardId(value);
    }
}
