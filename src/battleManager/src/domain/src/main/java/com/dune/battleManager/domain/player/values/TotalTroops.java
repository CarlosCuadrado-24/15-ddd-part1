package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class TotalTroops implements IValueObject {

    private final Integer value;

    private TotalTroops(Integer value) {
        this.value = value;
    }

    public static TotalTroops of(Integer value){
        return new TotalTroops (value);
    }

    @Override
    public void validate() {
        if(this.value == null){
            throw new IllegalArgumentException("The object can't be null");
        }

        if(this.value < 0){
            throw new IllegalArgumentException("The object can't be negative");
        }
    }

    public Integer getValue() {
        return value;
    }
}
