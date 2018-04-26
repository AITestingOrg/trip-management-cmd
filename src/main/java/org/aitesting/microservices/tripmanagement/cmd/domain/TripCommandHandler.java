package org.aitesting.microservices.tripmanagement.cmd.domain;

import org.aist.libs.eventsourcing.configuration.handlers.CustomCommandHandler;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.TripAggregate;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;


public class TripCommandHandler extends CustomCommandHandler<TripAggregate> {
    public TripCommandHandler(EventSourcingRepository repository, CommandBus commandBus) {
        super(repository, commandBus, TripAggregate.class);
    }
}
