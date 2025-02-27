package com.dune.battleManager.application.battle.PreparePlayerToBattle;

public class PreparePlayerToBattleResponse {

    private final String name;
    private final Boolean agentToBattle;
    private final Integer troopsToBattle;
    private final Integer battleStrength;

    public PreparePlayerToBattleResponse(String name, Boolean agentToBattle, Integer troopsToBattle, Integer battleStrength) {
        this.name = name;
        this.agentToBattle = agentToBattle;
        this.troopsToBattle = troopsToBattle;
        this.battleStrength = battleStrength;
    }

    public Boolean getAgentToBattle() {
        return agentToBattle;
    }

    public Integer getTroopsToBattle() {
        return troopsToBattle;
    }

    public Integer getBattleStrength() {
        return battleStrength;
    }

    public String getName() {
        return name;
    }
}
