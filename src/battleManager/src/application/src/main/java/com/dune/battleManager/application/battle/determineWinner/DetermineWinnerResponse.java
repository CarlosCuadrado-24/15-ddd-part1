package com.dune.battleManager.application.battle.determineWinner;

public class DetermineWinnerResponse {

    private final RewardConflict rewardConflict;
    private final String playerName;

    public DetermineWinnerResponse(RewardConflict rewardConflict, String playerName) {
        this.rewardConflict = rewardConflict;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public RewardConflict getRewardConflict() {
        return rewardConflict;
    }

    public static class RewardConflict {
        private final Integer victoryPoints;
        private final Integer troops;
        private final Integer resources;

        public RewardConflict(Integer victoryPoints, Integer troops, Integer resources) {
            this.victoryPoints = victoryPoints;
            this.troops = troops;
            this.resources = resources;
        }

        public Integer getVictoryPoints() {
            return victoryPoints;
        }

        public Integer getTroops() {
            return troops;
        }

        public Integer getResources() {
            return resources;
        }
    }

}
