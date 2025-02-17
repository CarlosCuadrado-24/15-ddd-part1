package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class BlockHiddenAbility implements IValueObject {

    private final Boolean value;

    private BlockHiddenAbility(Boolean value) {
        this.value = value;
    }

    public static BlockHiddenAbility of(Boolean value){
        return new BlockHiddenAbility (value);
    }

    @Override
    public void validate() {
        if(this.value == null){
            throw new IllegalArgumentException("The object can't be null");
        }
    }

    public Boolean getValue() {
        return value;
    }
}
