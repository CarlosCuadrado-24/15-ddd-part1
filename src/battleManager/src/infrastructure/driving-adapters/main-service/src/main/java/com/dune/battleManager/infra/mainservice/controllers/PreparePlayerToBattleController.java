package com.dune.battleManager.infra.mainservice.controllers;


import com.dune.battleManager.application.battle.PreparePlayerToBattle.PreparePlayerToBattleRequest;
import com.dune.battleManager.application.battle.PreparePlayerToBattle.PreparePlayerToBattleResponse;
import com.dune.battleManager.application.battle.PreparePlayerToBattle.PreparePlayerToBattleUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/prepare-player")
public class PreparePlayerToBattleController {

    private final PreparePlayerToBattleUseCase preparePlayerUseCase;

    public PreparePlayerToBattleController(PreparePlayerToBattleUseCase preparePlayerUseCase) {
        this.preparePlayerUseCase = preparePlayerUseCase;
    }

    @PostMapping
    public Mono<PreparePlayerToBattleResponse> execute (@RequestBody PreparePlayerToBattleRequest request){
        return preparePlayerUseCase.execute(request);
    }

}
