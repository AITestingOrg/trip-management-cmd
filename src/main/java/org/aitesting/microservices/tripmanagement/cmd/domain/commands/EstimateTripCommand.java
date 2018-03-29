package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class EstimateTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String originAddress;
    private String destinationAddress;
    private TripInvoice invoice;

    public EstimateTripCommand() {
    }

    public EstimateTripCommand(UUID id, TripDto trip) {
        this.id = id;
        this.originAddress = trip.getOriginAddress();
        this.destinationAddress = trip.getDestinationAddress();
        this.invoice = trip.getTripEstimate();
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

    public TripInvoice getInvoice() {
        return invoice;
    }
}
