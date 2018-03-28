package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class EstimateTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String originAddress;
    private String destinationAddress;

    public EstimateTripCommand() {
    }

    public EstimateTripCommand(UUID id, String originAddress, String destinationAddress) {
        this.id = id;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
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
}
