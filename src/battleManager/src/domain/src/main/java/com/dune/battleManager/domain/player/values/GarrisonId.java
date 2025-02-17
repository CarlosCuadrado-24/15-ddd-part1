package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.Identity;

public class GarrisonId extends Identity {

    public GarrisonId() {
        super();
    }

    private GarrisonId(String value) {
        super(value);
    }

    public static GarrisonId of(String value){
        return new GarrisonId(value);
    }

}
