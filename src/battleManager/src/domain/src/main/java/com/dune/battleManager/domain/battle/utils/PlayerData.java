package com.dune.battleManager.domain.battle.utils;

import java.util.ArrayList;

public class PlayerData {

    private String name;
    private Integer victoryPoints;
    private Boolean deployedAgent;
    private String alliance;
    private Integer battleStrength;

    private ArrayList<ResourceRequest> resources;
    private ArrayList<CombatIntrigueCardRequest> combatIntrigueCard;
    private LeaderRequest leader;
    private GarrisonRequest garrison;

    public PlayerData(String name, Integer victoryPoints, Boolean deployedAgent, String alliance, Integer battleStrength, ArrayList<ResourceRequest> resources, ArrayList<CombatIntrigueCardRequest> combatIntrigueCard, LeaderRequest leader, GarrisonRequest garrison) {
        this.name = name;
        this.victoryPoints = victoryPoints;
        this.deployedAgent = deployedAgent;
        this.alliance = alliance;
        this.battleStrength = battleStrength;
        this.resources = resources;
        this.combatIntrigueCard = combatIntrigueCard;
        this.leader = leader;
        this.garrison = garrison;
    }

    public PlayerData() {
        this.resources = new ArrayList<>();
        this.combatIntrigueCard = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getVictoryPoints() {
        return victoryPoints;
    }

    public Boolean getDeployedAgent() {
        return deployedAgent;
    }

    public String getAlliance() {
        return alliance;
    }

    public Integer getBattleStrength() {
        return battleStrength;
    }

    public ArrayList<ResourceRequest> getResources() {
        return resources;
    }

    public ArrayList<CombatIntrigueCardRequest> getCombatIntrigueCard() {
        return combatIntrigueCard;
    }

    public LeaderRequest getLeader() {
        return leader;
    }

    public GarrisonRequest getGarrison() {
        return garrison;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVictoryPoints(Integer victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void setDeployedAgent(Boolean deployedAgent) {
        this.deployedAgent = deployedAgent;
    }

    public void setAlliance(String alliance) {
        this.alliance = alliance;
    }

    public void setBattleStrength(Integer battleStrength) {
        this.battleStrength = battleStrength;
    }

    public void setResources(ArrayList<ResourceRequest> resources) {
        this.resources = resources;
    }

    public void setCombatIntrigueCard(ArrayList<CombatIntrigueCardRequest> combatIntrigueCard) {
        this.combatIntrigueCard = combatIntrigueCard;
    }

    public void setLeader(LeaderRequest leader) {
        this.leader = leader;
    }

    public void setGarrison(GarrisonRequest garrison) {
        this.garrison = garrison;
    }

    public static class ResourceRequest {
        private String type;
        private String description;

        public ResourceRequest(String type, String description) {
            this.type = type;
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class CombatIntrigueCardRequest {
        private String name;
        private String effect;
        private Integer stack;

        public CombatIntrigueCardRequest(String name, String effect, Integer stack) {
            this.name = name;
            this.effect = effect;
            this.stack = stack;
        }

        public String getName() {
            return name;
        }

        public String getEffect() {
            return effect;
        }

        public Integer getStack() {
            return stack;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public void setStack(Integer stack) {
            this.stack = stack;
        }
    }

    public static class LeaderRequest {
        private String name;
        private String hiddenAbilityName;
        private String hiddenAbilityResource;
        private Integer hiddenAbilityValue;
        private String hiddenAbilityExtra;

        private String houseRequest;
        private Integer difficultyLevel;
        private String permanentAbilityName;
        private String permanentAbilityResource;
        private Integer permanentAbilityValue;
        private Boolean blockHiddenAbility;

        public LeaderRequest(String name, String hiddenAbilityName, String hiddenAbilityResource, Integer hiddenAbilityValue, String hiddenAbilityExtra, String houseRequest, Integer difficultyLevel, String permanentAbilityName, String permanentAbilityResource, Integer permanentAbilityValue, Boolean blockHiddenAbility) {
            this.name = name;
            this.hiddenAbilityName = hiddenAbilityName;
            this.hiddenAbilityResource = hiddenAbilityResource;
            this.hiddenAbilityValue = hiddenAbilityValue;
            this.hiddenAbilityExtra = hiddenAbilityExtra;
            this.houseRequest = houseRequest;
            this.difficultyLevel = difficultyLevel;
            this.permanentAbilityName = permanentAbilityName;
            this.permanentAbilityResource = permanentAbilityResource;
            this.permanentAbilityValue = permanentAbilityValue;
            this.blockHiddenAbility = blockHiddenAbility;
        }

        public String getName() {
            return name;
        }

        public String getHiddenAbilityName() {
            return hiddenAbilityName;
        }

        public String getHiddenAbilityResource() {
            return hiddenAbilityResource;
        }

        public Integer getHiddenAbilityValue() {
            return hiddenAbilityValue;
        }

        public String getHiddenAbilityExtra() {
            return hiddenAbilityExtra;
        }

        public String getHouseRequest() {
            return houseRequest;
        }

        public Integer getDifficultyLevel() {
            return difficultyLevel;
        }

        public String getPermanentAbilityName() {
            return permanentAbilityName;
        }

        public String getPermanentAbilityResource() {
            return permanentAbilityResource;
        }

        public Integer getPermanentAbilityValue() {
            return permanentAbilityValue;
        }

        public Boolean getBlockHiddenAbility() {
            return blockHiddenAbility;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHiddenAbilityName(String hiddenAbilityName) {
            this.hiddenAbilityName = hiddenAbilityName;
        }

        public void setHiddenAbilityResource(String hiddenAbilityResource) {
            this.hiddenAbilityResource = hiddenAbilityResource;
        }

        public void setHiddenAbilityValue(Integer hiddenAbilityValue) {
            this.hiddenAbilityValue = hiddenAbilityValue;
        }

        public void setHiddenAbilityExtra(String hiddenAbilityExtra) {
            this.hiddenAbilityExtra = hiddenAbilityExtra;
        }

        public void setHouseRequest(String houseRequest) {
            this.houseRequest = houseRequest;
        }

        public void setDifficultyLevel(Integer difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }

        public void setPermanentAbilityName(String permanentAbilityName) {
            this.permanentAbilityName = permanentAbilityName;
        }

        public void setPermanentAbilityResource(String permanentAbilityResource) {
            this.permanentAbilityResource = permanentAbilityResource;
        }

        public void setPermanentAbilityValue(Integer permanentAbilityValue) {
            this.permanentAbilityValue = permanentAbilityValue;
        }

        public void setBlockHiddenAbility(Boolean blockHiddenAbility) {
            this.blockHiddenAbility = blockHiddenAbility;
        }
    }

    public static class GarrisonRequest {
        private Integer totalTroops;
        private Integer battleReadyTroops;

        public GarrisonRequest(Integer totalTroops, Integer battleReadyTroops) {
            this.totalTroops = totalTroops;
            this.battleReadyTroops = battleReadyTroops;
        }

        public Integer getTotalTroops() {
            return totalTroops;
        }

        public Integer getBattleReadyTroops() {
            return battleReadyTroops;
        }

        public void setTotalTroops(Integer totalTroops) {
            this.totalTroops = totalTroops;
        }

        public void setBattleReadyTroops(Integer battleReadyTroops) {
            this.battleReadyTroops = battleReadyTroops;
        }
    }

}
