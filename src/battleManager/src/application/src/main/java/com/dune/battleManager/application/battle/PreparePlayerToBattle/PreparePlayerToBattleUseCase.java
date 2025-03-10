package com.dune.battleManager.application.battle.PreparePlayerToBattle;

import com.dune.battleManager.application.battle.shared.ports.IEventsRepositoryPort;
import com.dune.battleManager.application.battle.shared.MapperPlayer;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class PreparePlayerToBattleUseCase implements ICommandUseCase <PreparePlayerToBattleRequest, Mono<PreparePlayerToBattleResponse>>{

    private final IEventsRepositoryPort repository;

    public PreparePlayerToBattleUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PreparePlayerToBattleResponse> execute(PreparePlayerToBattleRequest request) {
        Player player = MapperPlayer.mapper(request.getPlayer());

        player.deployAgentToBattle();
        player.deployTroopsToBattle(request.getRound());
        player.useLeaderHiddenAbility(request.getRound());
        player.calculateBattleStrength(request.getRound());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();


        return Mono.just(new PreparePlayerToBattleResponse(
                player.getName().getValue(),
                player.getDeployedAgent().getValue(),
                player.getGarrison().getBattleReadyTroops().getValue(),
                player.getBattleStrength().getValue()
        ));
    }
}
