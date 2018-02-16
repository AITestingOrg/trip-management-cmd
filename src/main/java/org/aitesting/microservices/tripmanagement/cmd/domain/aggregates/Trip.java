package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.common.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.TripCreatedEvent;
import org.aitesting.microservices.tripmanagement.common.TripStartedEvent;
import org.aitesting.microservices.tripmanagement.common.TripStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Trip {
    @AggregateIdentifier
    private UUID id;
    private String originAddress;
    private String destinationAddress;
    private UUID userId;
    private TripStatus status;

    @CommandHandler
    public Trip(CreateTripCommand createTripCommand){
        apply(new TripCreatedEvent(createTripCommand.getId(), createTripCommand.getUserId(),
                createTripCommand.getOriginAddress(), createTripCommand.getDestinationAddress()));
    }

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

    @CommandHandler
    public void on(CreateTripCommand createTripCommand) {
        apply(new TripCreatedEvent(createTripCommand.getId(), createTripCommand.getUserId(),
                createTripCommand.getOriginAddress(), createTripCommand.getDestinationAddress()));
    }

    @CommandHandler
    public void on(CancelTripCommand cancelTripCommand) {
        apply(new TripCanceledEvent((cancelTripCommand.getId())));
    }

    @CommandHandler
    public void on(StartTripCommand startTripCommand) {
        apply(new TripStartedEvent((startTripCommand.getId())));
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
}
