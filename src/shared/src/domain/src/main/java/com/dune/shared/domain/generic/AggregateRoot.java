package com.dune.shared.domain.generic;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I>{
    private final DomainActionsHandler actionsHandler = new DomainActionsHandler();

    protected AggregateRoot(I identity) {
        super(identity);
    }

    public List<DomainEvent> getUncommittedEvents(){
        return List.copyOf(actionsHandler.getEvents());
    }

    public void markEventsAsCommitted(){
        actionsHandler.getEvents().clear();
    }

    protected void subscribe(final DomainActionsContainer container){
        actionsHandler.subscribe(container);
    }

    protected void apply (final DomainEvent event){
        final String aggregateName = getIdentity().getClass().getSimpleName().replace("Id","").toLowerCase();
        event.setAggregateName(aggregateName);
        event.setAggregateRootId(getIdentity().getValue());
        actionsHandler.apply(event);

    }

}
