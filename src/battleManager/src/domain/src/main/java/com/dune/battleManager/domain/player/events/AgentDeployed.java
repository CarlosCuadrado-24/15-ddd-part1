package com.dune.battleManager.domain.player.events;


import com.dune.shared.domain.generic.DomainEvent;

public class AgentDeployed extends DomainEvent {

    public AgentDeployed() {
        super(EventsEnum.AGENT_DEPLOYED.name());
    }

}
