package com.dune.battleManager.application.battle.determineWinner;

import com.dune.shared.application.Request;

public class DetermineWinnerRequest extends Request {

    public DetermineWinnerRequest(String aggregateId) {
        super(aggregateId);
    }

}
