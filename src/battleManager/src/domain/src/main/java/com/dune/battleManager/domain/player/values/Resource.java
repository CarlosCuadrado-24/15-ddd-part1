package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class Resource implements IValueObject {

    private final String type;
    private final String description;

    private Resource(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public static Resource of(String type, String description) {
        return new Resource(type, description);
    }

    @Override
    public void validate() {
        if(!this.type.matches("^[a-zA-Z]+$") && !this.description.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("The type or description can't have special characters and numbers");
        }
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
