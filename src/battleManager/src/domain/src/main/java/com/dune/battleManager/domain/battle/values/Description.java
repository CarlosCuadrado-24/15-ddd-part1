package com.dune.battleManager.domain.battle.values;


import com.dune.shared.domain.generic.IValueObject;

public class Description implements IValueObject {

    private final String value;

    private Description(String value) {
        this.value = value;
        validate();
    }

    public static Description of(String value){
        return new Description(value);
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
