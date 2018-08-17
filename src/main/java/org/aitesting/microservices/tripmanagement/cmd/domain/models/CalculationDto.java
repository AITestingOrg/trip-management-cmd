package org.aitesting.microservices.tripmanagement.cmd.domain.models;

import java.util.UUID;

public class CalculationDto {
    private String origin;
    private String destination;
    private UUID userId;
    private long departureTime;

    public CalculationDto() {
    }

    public CalculationDto(String origin, String destination, long departureTime, UUID userId) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.userId = userId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public long getDeparture_time() {
        return departureTime;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return String.format("origin: %s\ndestination: %s\ndepartureTime: %s\n",
                origin, destination, departureTime);
    }
}
