package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

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
