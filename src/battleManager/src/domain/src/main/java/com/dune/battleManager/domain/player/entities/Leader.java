package com.dune.battleManager.domain.player.entities;

import com.dune.battleManager.domain.player.values.BlockHiddenAbility;
import com.dune.battleManager.domain.player.values.CombatIntrigueCard;
import com.dune.battleManager.domain.player.values.DifficultyLevel;
import com.dune.battleManager.domain.player.values.Hause;
import com.dune.battleManager.domain.player.values.HiddenAbility;
import com.dune.battleManager.domain.player.values.LeaderId;
import com.dune.battleManager.domain.player.values.Name;
import com.dune.battleManager.domain.player.values.PermanentAbility;
import com.dune.shared.domain.generic.Entity;

public class Leader extends Entity<LeaderId> {

    private Name name;
    private Hause hause;
    private DifficultyLevel difficultyLevel;
    private PermanentAbility permanentAbility;
    private HiddenAbility hiddenAbility;
    private BlockHiddenAbility blockHiddenAbility;

    public Leader(Name name, HiddenAbility hiddenAbility, Hause hause, DifficultyLevel difficultyLevel, PermanentAbility permanentAbility, BlockHiddenAbility blockHiddenAbility) {
        super(new LeaderId());
        this.name = name;
        this.hiddenAbility = hiddenAbility;
        this.hause = hause;
        this.difficultyLevel = difficultyLevel;
        this.permanentAbility = permanentAbility;
        this.blockHiddenAbility = blockHiddenAbility;
    }

    public Leader(LeaderId identity, Name name, HiddenAbility hiddenAbility, Hause hause, DifficultyLevel difficultyLevel, PermanentAbility permanentAbility) {
        super(identity);
        this.name = name;
        this.hiddenAbility = hiddenAbility;
        this.hause = hause;
        this.difficultyLevel = difficultyLevel;
        this.permanentAbility = permanentAbility;
    }

    public void unblockHiddenAbility(String combatIntrigueCard){
        if(this.hiddenAbility.getCardNameToUnlock().equals(combatIntrigueCard)){
            this.setBlockHiddenAbility(BlockHiddenAbility.of(false));
        }
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Hause getHause() {
        return hause;
    }

    public void setHause(Hause hause) {
        this.hause = hause;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public PermanentAbility getPermanentAbility() {
        return permanentAbility;
    }

    public void setPermanentAbility(PermanentAbility permanentAbility) {
        this.permanentAbility = permanentAbility;
    }

    public HiddenAbility getHiddenAbility() {
        return hiddenAbility;
    }

    public void setHiddenAbility(HiddenAbility hiddenAbility) {
        this.hiddenAbility = hiddenAbility;
    }

    public BlockHiddenAbility getBlockHiddenAbility() {
        return blockHiddenAbility;
    }

    public void setBlockHiddenAbility(BlockHiddenAbility blockHiddenAbility) {
        this.blockHiddenAbility = blockHiddenAbility;
    }
}
