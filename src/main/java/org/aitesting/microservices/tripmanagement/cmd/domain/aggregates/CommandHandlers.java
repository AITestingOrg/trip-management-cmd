package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CompleteTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.common.events.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripCompletedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripStartedEvent;
import org.axonframework.commandhandling.CommandHandler;

public class CommandHandlers {
    @CommandHandler
    public void on(CancelTripCommand cancelTripCommand) {
        apply(new TripCanceledEvent((cancelTripCommand.getId())));
    }

    @CommandHandler
    public void on(StartTripCommand startTripCommand) {
        apply(new TripStartedEvent((startTripCommand.getId())));
    }

    @CommandHandler
    public void on(CompleteTripCommand completeTripCommand) {
        apply(new TripCompletedEvent((completeTripCommand.getId())));
    }
}
