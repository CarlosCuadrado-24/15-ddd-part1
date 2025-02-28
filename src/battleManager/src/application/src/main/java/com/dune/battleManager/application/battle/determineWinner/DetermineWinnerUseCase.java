package com.dune.battleManager.application.battle.determineWinner;


import com.dune.battleManager.application.battle.shared.ports.IEventsRepositoryPort;
import com.dune.battleManager.domain.battle.Battle;
import com.dune.shared.application.ICommandUseCase;

import com.dune.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;


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
                    events.sort(Comparator.comparing(DomainEvent::getWhen));
                    battle.applyTerritoryBonus();
                    battle.applyTerritoryCurse();
//                    System.out.println(battle.getPlayers().get(0).getName().getValue());
                    battle.determineConflictWinner();
//                    try {
////                        System.out.println("Esperando...");
//                        TimeUnit.SECONDS.sleep(1);
//                        battle.determineConflictWinner();
////                        System.out.println("Listo!");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


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
