package com.dune.battleManager.application.battle.PreparePlayerToBattle;

public class PreparePlayerToBattleResponse {

    private final Boolean agentToBattle;
    private final Integer troopsToBattle;
    private final Integer battleStrength;

    public PreparePlayerToBattleResponse(Boolean agentToBattle, Integer troopsToBattle, Integer battleStrength) {
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
}
