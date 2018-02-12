package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import org.aitesting.microservices.tripmanagement.common.TripStatus;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private UUID userId;
    private String originAddress;
    private String destinationAddress;

    public CreateTripCommand(UUID userId, String originAddress, String destinationAddress){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
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
}
