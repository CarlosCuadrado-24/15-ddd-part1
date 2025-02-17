package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.Identity;

public class LeaderId extends Identity {
    public LeaderId() {
        super();
    }

    private LeaderId(String value) {
        super(value);
    }

    public static LeaderId of(String value){
        return new LeaderId(value);
    }
}
