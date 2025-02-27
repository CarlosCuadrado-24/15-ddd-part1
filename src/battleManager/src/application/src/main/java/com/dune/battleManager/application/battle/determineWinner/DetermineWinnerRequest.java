package com.dune.battleManager.application.battle.determineWinner;

import com.dune.shared.application.Request;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetermineWinnerRequest extends Request {

    public DetermineWinnerRequest(@JsonProperty("aggregateId") String aggregateId) {
        super(aggregateId);
    }

}
