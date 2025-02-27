package com.dune.battleManager.application.battle.InitializeBattle;

import com.dune.battleManager.application.battle.shared.MapperPlayer;
import com.dune.battleManager.application.battle.shared.ports.IEventsRepositoryPort;
import com.dune.battleManager.domain.battle.Battle;
import com.dune.battleManager.domain.battle.entities.ConflictCard;
import com.dune.battleManager.domain.battle.entities.Territory;
import com.dune.battleManager.domain.player.Player;
import com.dune.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InitializeBattleUseCase  implements ICommandUseCase<InitializeBattleRequest, Mono<InitializeBattleResponse>> {
    private final IEventsRepositoryPort repository;

    public InitializeBattleUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<InitializeBattleResponse> execute(InitializeBattleRequest request) {
        Battle battle = new Battle();

        ArrayList<Player> players = request.getPlayers()
                .stream()
                .map(MapperPlayer::mapper)
                .collect(Collectors.toCollection(ArrayList::new));


//          battle.setPlayers(players);
          battle.loadPlayers(players);
          battle.confirmParticipants();
          System.out.println(battle.getPlayers().get(0).getName().getValue());

        List<InitializeBattleResponse.PlayerGame> playerGames = battle.getPlayers().stream()
                .map(player -> new InitializeBattleResponse.PlayerGame(
                        player.getName().getValue(),
                        player.getVictoryPoints().getValue(),
                        player.getGarrison().getBattleReadyTroops().getValue(),
                        player.getResources().size()
                ))
                .collect(Collectors.toList());

        Territory territory = battle.getTerritory();
        InitializeBattleResponse.TerritoryGame territoryGame = new InitializeBattleResponse.TerritoryGame(
                territory.getName().getValue(),
                territory.getCurse().getEffect(),
                territory.getCurse().getStack(),
                territory.getBonus().getEffect(),
                territory.getBonus().getStack()
        );

        ConflictCard conflictCard = battle.getConflictCard();
        InitializeBattleResponse.ConflictCardGame conflictCardGame = new InitializeBattleResponse.ConflictCardGame(
                conflictCard.getName().getValue(),
                conflictCard.getReward().getVictoryPoints(),
                conflictCard.getReward().getTroops(),
                conflictCard.getReward().getResources()
        );

        battle.getUncommittedEvents().forEach(repository::save);
        battle.markEventsAsCommitted();

        return Mono.just(new InitializeBattleResponse(
                new ArrayList<>(playerGames),
                conflictCardGame,
                territoryGame,
                battle.getIdentity().getValue()
        ));
    }
}
