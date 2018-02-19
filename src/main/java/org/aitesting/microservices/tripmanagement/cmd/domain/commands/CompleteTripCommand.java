package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CompleteTripCommand {
    @TargetAggregateIdentifier
    private UUID id;

    public CompleteTripCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() { return id; }
}
