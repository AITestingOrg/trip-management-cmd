package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.UUID;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.common.events.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripCompletedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripCreatedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripStartedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Trip {
    @AggregateIdentifier
    private UUID id;
    private String originAddress;
    private String destinationAddress;
    private UUID userId;
    private TripStatus status;

    @CommandHandler
    public Trip(CreateTripCommand createTripCommand) {
        apply(new TripCreatedEvent(createTripCommand.getId(), createTripCommand.getUserId(),
                createTripCommand.getOriginAddress(), createTripCommand.getDestinationAddress()));
    }

    @AggregateMember
    private CommandHandlers commandHandlers = new CommandHandlers();

    public Trip(){
    }

    public UUID getId() {
        return id;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public TripStatus getStatus() {
        return status;
    }

    @EventSourcingHandler
    public void on(TripCreatedEvent tripCreatedEvent) {
        id = tripCreatedEvent.getId();
        originAddress = tripCreatedEvent.getOriginAddress();
        destinationAddress = tripCreatedEvent.getDestinationAddress();
        userId = tripCreatedEvent.getUserId();
        status = TripStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(TripCanceledEvent tripCanceledEvent) {
        status = TripStatus.CANCELED;
    }

    @EventSourcingHandler
    public void on(TripStartedEvent tripStartedEvent) {
        status = TripStatus.STARTED;
    }

    @EventSourcingHandler
    public void on(TripCompletedEvent tripEndedEvent) {
        status = TripStatus.COMPLETED;
    }
}
