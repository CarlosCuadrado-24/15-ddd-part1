package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class Name implements IValueObject {

    private final String value;

    private Name(String value) {
        this.value = value;
        validate();
    }

    public static Name of(String value){
        return new Name(value);
    }

    @Override
    public void validate() {
        if(this.value==null){
            throw new IllegalArgumentException("The object can't be null");
        }
    }

    public String getValue() {
        return value;
    }
}
