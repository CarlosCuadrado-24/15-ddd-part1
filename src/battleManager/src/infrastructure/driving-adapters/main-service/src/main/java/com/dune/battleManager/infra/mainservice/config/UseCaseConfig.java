package com.dune.battleManager.infra.mainservice.config;

import com.dune.battleManager.application.battle.InitializeBattle.InitializeBattleUseCase;
import com.dune.battleManager.application.battle.determineWinner.DetermineWinnerUseCase;


import com.dune.battleManager.application.battle.PreparePlayerToBattle.PreparePlayerToBattleUseCase;
import com.dune.battleManager.infra.mongo.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public InitializeBattleUseCase initializeBattleUseCase(MongoAdapter adapter){
        return new InitializeBattleUseCase(adapter);
    }

    @Bean
    public PreparePlayerToBattleUseCase preparePlayerToBattleUseCase(MongoAdapter adapter){
        return new PreparePlayerToBattleUseCase(adapter);
    }

    @Bean
    public DetermineWinnerUseCase determineWinnerUseCase(MongoAdapter adapter){
        return new DetermineWinnerUseCase(adapter);
    }

}
