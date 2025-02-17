package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class PermanentAbility implements IValueObject {

    private final String name;
    private final String effect;
    private final Integer stack;


    private PermanentAbility(String name, String effect, Integer stack) {
        this.name = name;
        this.effect = effect;
        this.stack = stack;

    }

    public static PermanentAbility of(String name, String effect, Integer stack){
        return new PermanentAbility(name, effect, stack);
    }

    @Override
    public void validate() {

        if(!this.name.matches("^[a-zA-Z]+$") && !this.effect.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("The name or effect can't have special characters and numbers");
        }

        if(this.stack == null){
            throw new IllegalArgumentException("The stack can't be null");
        }

    }

    public String getName() {
        return name;
    }

    public Integer getStack() {
        return stack;
    }

    public String getEffect() {
        return effect;
    }

}
