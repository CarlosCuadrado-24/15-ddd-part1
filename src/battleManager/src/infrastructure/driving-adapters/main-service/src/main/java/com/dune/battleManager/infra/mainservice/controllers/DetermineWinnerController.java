package com.dune.battleManager.infra.mainservice.controllers;

import com.dune.battleManager.application.battle.determineWinner.DetermineWinnerRequest;
import com.dune.battleManager.application.battle.determineWinner.DetermineWinnerResponse;
import com.dune.battleManager.application.battle.determineWinner.DetermineWinnerUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/winner")
public class DetermineWinnerController {

    private final DetermineWinnerUseCase winnerUseCase;

    public DetermineWinnerController(DetermineWinnerUseCase winnerUseCase) {
        this.winnerUseCase = winnerUseCase;
    }

    @PostMapping
    public Mono<DetermineWinnerResponse> execute(@RequestBody DetermineWinnerRequest request){
        return winnerUseCase.execute(request);
    }

}
