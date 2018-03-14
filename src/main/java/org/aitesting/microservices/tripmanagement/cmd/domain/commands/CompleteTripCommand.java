package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CompleteTripCommand {
    @TargetAggregateIdentifier
    private UUID id;

    public CompleteTripCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
