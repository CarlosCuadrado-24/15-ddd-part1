package com.dune.battleManager.domain.battle.values;


import com.dune.shared.domain.generic.IValueObject;

public class IntensityLevel implements IValueObject {

    private final Integer value;

    private IntensityLevel(Integer value) {
        this.value = value;
    }

    public static IntensityLevel of(Integer value){
        return new IntensityLevel (value);
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
