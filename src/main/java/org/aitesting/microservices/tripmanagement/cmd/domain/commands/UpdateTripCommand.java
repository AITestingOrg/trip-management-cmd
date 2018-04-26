package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class UpdateTripCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private TripDto trip;

    public UpdateTripCommand(UUID id, TripDto trip) {
        this.id = id;
        this.trip = trip;
    }

    public TripDto getTrip() {
        return trip;
    }

    public UUID getId() {
        return id;
    }
}
