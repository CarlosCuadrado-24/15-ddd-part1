package com.dune.battleManager.application.battle.shared;

import com.dune.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
    Flux<DomainEvent> findAllAggregates();
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
