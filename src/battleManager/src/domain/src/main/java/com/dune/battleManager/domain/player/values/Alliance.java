package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class Alliance implements IValueObject {

    private final String value;

    private Alliance(String value) {
        this.value = value;
        validate();
    }

    public static Alliance of(String value){
        return new Alliance(value);
    }

    @Override
    public void validate() {
        if(!this.value.matches("^[a-zA-Z0-9]+$")){
            throw new IllegalArgumentException("The object can't have special characters");
        }
    }

    public String getValue() {
        return value;
    }
}
