package com.dune.battleManager.domain.battle.values;


import com.dune.shared.domain.generic.IValueObject;

public class Banner implements IValueObject {

    private final String value;

    private Banner(String value) {
        this.value = value;
        validate();
    }

    public static Banner of(String value){
        return new Banner(value);
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
