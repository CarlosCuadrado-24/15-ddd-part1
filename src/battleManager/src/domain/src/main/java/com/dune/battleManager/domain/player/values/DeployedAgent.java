package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class DeployedAgent implements IValueObject {

    private final Boolean value;

    private DeployedAgent(Boolean value) {
        this.value = value;
    }

    public static DeployedAgent of(Boolean value){
        return new DeployedAgent (value);
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
