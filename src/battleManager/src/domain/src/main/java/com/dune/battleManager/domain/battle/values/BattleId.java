package com.dune.battleManager.domain.battle.values;

import com.dune.shared.domain.generic.Identity;

public class BattleId extends Identity {

    public BattleId() {
        super();
    }

    private BattleId(String value) {
        super(value);
    }

    public static BattleId of(String value){
        return new BattleId(value);
    }

}
