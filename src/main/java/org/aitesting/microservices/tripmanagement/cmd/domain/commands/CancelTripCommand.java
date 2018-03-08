package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CancelTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    public CancelTripCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
