package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import com.dune.battleManager.application.battle.determineWinner.DetermineWinnerResponse;
import com.dune.battleManager.application.battle.shared.IEventsRepository;
import com.dune.battleManager.domain.battle.Battle;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class PreparePlayerToBattleUseCase implements ICommandUseCase <PreparePlayerToBattleRequest, Mono<PreparePlayerToBattleResponse>>{

    private final IEventsRepository repository;

    public PreparePlayerToBattleUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PreparePlayerToBattleResponse> execute(PreparePlayerToBattleRequest request) {
        Player player = request.getPlayer();
        player.deployAgentToBattle();
        player.deployTroopsToBattle(request.getRound());
        player.useLeaderHiddenAbility(request.getRound());
        player.calculateBattleStrength(request.getRound());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();


        return Mono.just(new PreparePlayerToBattleResponse(
                player.getDeployedAgent().getValue(),
                player.getGarrison().getBattleReadyTroops().getValue(),
                player.getBattleStrength().getValue()
        ));

//        return repository
//                .findEventsByAggregateId(request.getAggregateId())
//                .collectList()
//                .flatMap(events -> {
//                    Player player = Player.from(request.getAggregateId(), events);
//                    player.deployAgentToBattle();
//                    player.deployTroopsToBattle(request.getRound());
//                    player.useLeaderHiddenAbility(request.getRound());
//                    player.calculateBattleStrength(request.getRound());
//
//                    player.getUncommittedEvents().forEach(repository::save);
//                    player.markEventsAsCommitted();
//                });
    }
}
