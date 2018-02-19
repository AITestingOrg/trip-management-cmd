package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import org.aitesting.microservices.tripmanagement.common.TripCanceledEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

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
