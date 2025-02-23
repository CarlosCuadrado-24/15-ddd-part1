package com.dune.battleManager.application.battle.InitializeBattle;

import java.util.ArrayList;

public class InitializeBattleResponse {
    private final ArrayList<PlayerGame> players;
    private final ConflictCardGame conflictCard;
    private final TerritoryGame territoryGame;

    public InitializeBattleResponse(ArrayList<PlayerGame> players, ConflictCardGame conflictCards, TerritoryGame territoryGame) {
        this.players = players;
        this.conflictCard = conflictCards;
        this.territoryGame = territoryGame;
    }


    public ArrayList<PlayerGame> getPlayers() {
        return players;
    }

    public ConflictCardGame getConflictCard() {
        return conflictCard;
    }

    public TerritoryGame getTerritoryGame() {
        return territoryGame;
    }

    public static class PlayerGame{
        private final String name;
        private final Integer victoryPoints;
        private final Integer troops;
        private final Integer resources;

        public PlayerGame(String name, Integer victoryPoints, Integer troops, Integer resources) {
            this.name = name;
            this.victoryPoints = victoryPoints;
            this.troops = troops;
            this.resources = resources;
        }

        public String getName() {
            return name;
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


    public static class ConflictCardGame{
        private final String name;
        private final Integer victoryPoints;
        private final Integer troops;
        private final Integer resources;

        public ConflictCardGame(String name, Integer victoryPoints, Integer troops, Integer resources) {
            this.name = name;
            this.victoryPoints = victoryPoints;
            this.troops = troops;
            this.resources = resources;
        }

        public String getName() {
            return name;
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


    public static class TerritoryGame{
        private final String name;
        private final String curse;
        private final Integer curseStack;
        private final String bonus;
        private final Integer bonusStack;

        public TerritoryGame(String name, String curse, Integer curseStack, String bonus, Integer bonusStack) {
            this.name = name;
            this.curse = curse;
            this.curseStack = curseStack;
            this.bonus = bonus;
            this.bonusStack = bonusStack;
        }

        public String getName() {
            return name;
        }

        public String getCurse() {
            return curse;
        }

        public Integer getCurseStack() {
            return curseStack;
        }

        public String getBonus() {
            return bonus;
        }

        public Integer getBonusStack() {
            return bonusStack;
        }
    }

}
