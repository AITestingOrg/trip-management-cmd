package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CompleteTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.common.events.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripCompletedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripStartedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHandlers {
    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    @CommandHandler
    public void on(CancelTripCommand cancelTripCommand) {
        logger.trace(String.format("Dispatching TripCanceledEvent %s", cancelTripCommand.getId()));
        apply(new TripCanceledEvent((cancelTripCommand.getId())));
    }

    @CommandHandler
    public void on(StartTripCommand startTripCommand) {
        logger.trace(String.format("Dispatching TripStarted %s", startTripCommand.getId()));
        apply(new TripStartedEvent((startTripCommand.getId())));
    }

    @CommandHandler
    public void on(CompleteTripCommand completeTripCommand) {
        logger.trace(String.format("Dispatching TripCompleted %s", completeTripCommand.getId()));
        apply(new TripCompletedEvent((completeTripCommand.getId())));
    }
}
