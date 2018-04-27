package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class AcceptTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private UUID driverId;

    public AcceptTripCommand(UUID id, UUID driverId) {
        this.id = id;
        this.driverId = driverId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getDriverId() {
        return driverId;
    }
}
