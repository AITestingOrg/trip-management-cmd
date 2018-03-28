package org.aitesting.microservices.tripmanagement.cmd.domain.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.UUID;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.*;
import org.aitesting.microservices.tripmanagement.cmd.service.services.CalculationService;
import org.aitesting.microservices.tripmanagement.common.events.*;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class Trip {
    private static final Logger logger = LoggerFactory.getLogger(Trip.class);

    @Autowired
    private CalculationService calculationService;

    @AggregateIdentifier
    private UUID id;
    private String originAddress;
    private String destinationAddress;
    private UUID userId;
    private TripStatus status;
    private TripInvoice estimateInvoice;

    @CommandHandler
    public Trip(CreateTripCommand createTripCommand) {
        logger.trace(String.format("Dispatching TripCreatedEvent %s", createTripCommand.getId()));
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

    public TripInvoice getEstimateInvoice() {
        return estimateInvoice;
    }

    /*
    Comand Handlers
     */

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

    @CommandHandler
    public void on(UpdateTripCommand updateTripCommand) {
        logger.trace(String.format("Dispatching UpdateTrip %s", updateTripCommand.getId()));
        apply(new TripUpdatedEvent(updateTripCommand.getId(),
                updateTripCommand.getTrip().getUserId(),
                updateTripCommand.getTrip().getOriginAddress(),
                updateTripCommand.getTrip().getDestinationAddress(),
                updateTripCommand.getTrip().getTripEstimate()));
    }

    @CommandHandler
    public void on(EstimateTripCommand estimateTripCommand) {
        logger.trace("Estimate Trip Command {}", estimateTripCommand.getId());
        try {
            TripInvoice invoice = calculationService.getInvoice(this);
            apply(new TripEstimatedEvent(estimateTripCommand.getId(), invoice));
            logger.info("Estimate Trip Command Success {}", estimateTripCommand.getId());
        } catch (Exception e) {
            apply(new TripEstimatedEvent(estimateTripCommand.getId(), null));
            logger.error("Estimate Trip Command ERROR {}: {}", estimateTripCommand.getId(), e.getMessage());
        }
    }

    /*
    Event Sourcing Handlers
     */

    @EventSourcingHandler
    public void on(TripCreatedEvent tripCreatedEvent) {
        logger.trace(String.format("Sourcing TripCreated %s", tripCreatedEvent.getId()));
        id = tripCreatedEvent.getId();
        originAddress = tripCreatedEvent.getOriginAddress();
        destinationAddress = tripCreatedEvent.getDestinationAddress();
        userId = tripCreatedEvent.getUserId();
        status = TripStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(TripUpdatedEvent tripCreatedEvent) {
        logger.trace(String.format("Sourcing TripUpdated %s", tripCreatedEvent.getId()));
        originAddress = tripCreatedEvent.getOriginAddress();
        destinationAddress = tripCreatedEvent.getDestinationAddress();
        userId = tripCreatedEvent.getUserId();
        status = TripStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(TripCanceledEvent tripCanceledEvent) {
        logger.trace(String.format("Sourcing TripCancelled  %s", tripCanceledEvent.getId()));
        status = TripStatus.CANCELED;
    }

    @EventSourcingHandler
    public void on(TripStartedEvent tripStartedEvent) {
        logger.trace(String.format("Sourcing TripStarted %s", tripStartedEvent.getId()));
        status = TripStatus.STARTED;
    }

    @EventSourcingHandler
    public void on(TripCompletedEvent tripEndedEvent) {
        logger.trace(String.format("Sourcing TripCompleted %s", tripEndedEvent.getId()));
        status = TripStatus.COMPLETED;
    }

    @EventSourcingHandler
    public void on(TripEstimatedEvent tripEstimatedEvent) {
        logger.trace(String.format("Sourcing TripEstimated %s", tripEstimatedEvent.getId()));
        if (tripEstimatedEvent.getTripInvoice() != null) {
            estimateInvoice = tripEstimatedEvent.getTripInvoice();
        } else {
            logger.warn(String.format("No invoice found %s", tripEstimatedEvent.getId()));
        }
    }
}
