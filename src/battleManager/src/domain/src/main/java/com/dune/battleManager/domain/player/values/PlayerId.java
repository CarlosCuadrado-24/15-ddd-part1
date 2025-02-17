package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.Identity;

public class PlayerId extends Identity {
    public PlayerId() {
        super();
    }

    private PlayerId(String value) {
        super(value);
    }

    public static PlayerId of(String value){
        return new PlayerId(value);
    }
}
