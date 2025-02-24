package com.dune.battleManager.application.battle.determineWinner;


import com.dune.battleManager.application.battle.shared.ports.IEventsRepositoryPort;
import com.dune.battleManager.domain.battle.Battle;
import com.dune.shared.application.ICommandUseCase;

import reactor.core.publisher.Mono;


public class DetermineWinnerUseCase implements ICommandUseCase<DetermineWinnerRequest,Mono<DetermineWinnerResponse>> {

    private final IEventsRepositoryPort repository;

    public DetermineWinnerUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DetermineWinnerResponse> execute(DetermineWinnerRequest request) {
        return repository
                .findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .flatMap(events -> {
                    Battle battle = Battle.from(request.getAggregateId(), events);
                    battle.applyTerritoryBonus();
                    battle.applyTerritoryCurse();
                    battle.determineConflictWinner();

                    battle.getUncommittedEvents().forEach(repository::save);
                    battle.markEventsAsCommitted();

                    DetermineWinnerResponse.RewardConflict rewardConflict = new DetermineWinnerResponse.RewardConflict(battle.getConflictCard().getReward().getVictoryPoints(),battle.getConflictCard().getReward().getTroops(),battle.getConflictCard().getReward().getResources());

                    return Mono.just(new DetermineWinnerResponse(
                            rewardConflict,
                            battle.getWinner().getName().getValue()
                    ));
                });
    }
}
