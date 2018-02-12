package org.aitesting.microservices.tripmanagement.cmd.domain;

import org.aist.libs.eventsourcing.configuration.handlers.CustomCommandHandler;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;

public class TripCommandHandler extends CustomCommandHandler<Trip> {
    public TripCommandHandler(EventSourcingRepository repository, CommandBus commandBus) {
        super(repository, commandBus, Trip.class);
    }
}
