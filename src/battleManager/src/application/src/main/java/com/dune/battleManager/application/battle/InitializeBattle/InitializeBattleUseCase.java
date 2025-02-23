package com.dune.battleManager.application.battle.InitializeBattle;

import com.dune.battleManager.application.battle.shared.IEventsRepository;
import com.dune.battleManager.domain.battle.Battle;
import com.dune.battleManager.domain.battle.entities.ConflictCard;
import com.dune.battleManager.domain.battle.entities.Territory;
import com.dune.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InitializeBattleUseCase  implements ICommandUseCase<InitializeBattleRequest, Mono<InitializeBattleResponse>> {
    private final IEventsRepository repository;

    public InitializeBattleUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<InitializeBattleResponse> execute(InitializeBattleRequest request) {
        Battle battle = new Battle();
        battle.loadPlayers(request.getPlayers());
        battle.confirmParticipants();

        List<InitializeBattleResponse.PlayerGame> playerGames = battle.getPlayers().stream()
                .map(player -> new InitializeBattleResponse.PlayerGame(
                        player.getName().getValue(),
                        player.getVictoryPoints().getValue(),
                        player.getGarrison().getTotalTroops().getValue(),
                        player.getResources().size()
                ))
                .collect(Collectors.toList());

        ConflictCard conflictCard = battle.getConflictCard();
        InitializeBattleResponse.ConflictCardGame conflictCardGame = new InitializeBattleResponse.ConflictCardGame(
                conflictCard.getName().getValue(),
                conflictCard.getReward().getVictoryPoints(),
                conflictCard.getReward().getTroops(),
                conflictCard.getReward().getResources()
        );

        Territory territory = battle.getTerritory();
        InitializeBattleResponse.TerritoryGame territoryGame = new InitializeBattleResponse.TerritoryGame(
                territory.getName().getValue(),
                territory.getCurse().getEffect(),
                territory.getCurse().getStack(),
                territory.getBonus().getEffect(),
                territory.getBonus().getStack()
        );

        battle.getUncommittedEvents().forEach(repository::save);
        battle.markEventsAsCommitted();

        return Mono.just(new InitializeBattleResponse(
                new ArrayList<>(playerGames),
                conflictCardGame,
                territoryGame
        ));
    }
}
