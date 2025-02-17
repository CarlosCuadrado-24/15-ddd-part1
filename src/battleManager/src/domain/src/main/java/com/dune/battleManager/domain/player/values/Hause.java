package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class Hause implements IValueObject {

    private final String value;

    private Hause(String value) {
        this.value = value;
        validate();
    }

    public static Hause of(String value){
        return new Hause(value);
    }

    @Override
    public void validate() {
        if(!this.value.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("The object can't have special characters and numbers");
        }
    }

    public String getValue() {
        return value;
    }
}
