package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class DifficultyLevel implements IValueObject {

    private final Integer value;

    private DifficultyLevel(Integer value) {
        this.value = value;
    }

    public static DifficultyLevel of(Integer value){
        return new DifficultyLevel (value);
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
