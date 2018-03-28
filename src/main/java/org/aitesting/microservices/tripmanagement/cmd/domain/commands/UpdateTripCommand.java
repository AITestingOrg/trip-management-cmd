package org.aitesting.microservices.tripmanagement.cmd.domain.commands;

import java.util.UUID;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.axonframework.commandhandling.model.AggregateIdentifier;

public class UpdateTripCommand {
    @AggregateIdentifier
    private UUID id;
    private TripDto trip;

    public UpdateTripCommand() {
    }

    public UpdateTripCommand(UUID id, TripDto trip) {
        this.trip = trip;
        this.id = id;
    }

    public TripDto getTrip() {
        return trip;
    }

    public UUID getId() {
        return id;
    }
}
