package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.common.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.TripStartedEvent;
import org.axonframework.commandhandling.CommandHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class CommandHandlers {

    @CommandHandler
    public void on(CancelTripCommand cancelTripCommand) {
        apply(new TripCanceledEvent((cancelTripCommand.getId())));
    }

    @CommandHandler
    public void on(StartTripCommand startTripCommand) {
        apply(new TripStartedEvent((startTripCommand.getId())));
    }
}
