package com.dune.battleManager.infra.mainservice.controllers;

import com.dune.battleManager.application.battle.InitializeBattle.InitializeBattleRequest;
import com.dune.battleManager.application.battle.InitializeBattle.InitializeBattleResponse;
import com.dune.battleManager.application.battle.InitializeBattle.InitializeBattleUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/initialize-battle")
public class InitializeBattleController {

    private final InitializeBattleUseCase initBattleUseCase;

    public InitializeBattleController(InitializeBattleUseCase initBattleUseCase) {
        this.initBattleUseCase = initBattleUseCase;
    }

    @PostMapping
    public Mono<InitializeBattleResponse> execute(@RequestBody InitializeBattleRequest request){
        return initBattleUseCase.execute(request);
    }
}
