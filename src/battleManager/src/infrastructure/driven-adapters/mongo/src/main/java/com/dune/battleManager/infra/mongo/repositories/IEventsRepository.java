package com.dune.battleManager.infra.mongo.repositories;


import com.dune.battleManager.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
