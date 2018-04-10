package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;

import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private UUID userId;
    private String originAddress;
    private String destinationAddress;
    private TripInvoice tripInvoice;

    public CreateTripCommand(UUID userId, String originAddress, String destinationAddress, TripInvoice tripInvoice) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.tripInvoice = tripInvoice;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public TripInvoice getTripInvoice() { return tripInvoice; }

    public void setTripInvoice(TripInvoice tripInvoice) { this.tripInvoice = tripInvoice; }
}
