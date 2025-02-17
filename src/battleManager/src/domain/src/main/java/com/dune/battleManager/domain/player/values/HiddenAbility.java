package com.dune.battleManager.domain.player.values;

import com.dune.shared.domain.generic.IValueObject;

public class HiddenAbility implements IValueObject {

    private final String name;
    private final String effect;
    private final Integer stack;
    private final String cardNameToUnlock;

    private HiddenAbility(String name, String effect, Integer stack,String cardNameToUnlock) {
        this.name = name;
        this.effect = effect;
        this.stack = stack;
        this.cardNameToUnlock = cardNameToUnlock;
    }

    public static HiddenAbility of(String name, String effect, Integer stack, String cardNameToUnlock){
        return new HiddenAbility(name, effect, stack,cardNameToUnlock);
    }

    @Override
    public void validate() {

        if(!this.name.matches("^[a-zA-Z]+$") && !this.effect.matches("^[a-zA-Z]+$") && !this.cardNameToUnlock.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("The name or effect or cardNameToUnlock can't have special characters and numbers");
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

    public String getCardNameToUnlock() {
        return cardNameToUnlock;
    }

}
