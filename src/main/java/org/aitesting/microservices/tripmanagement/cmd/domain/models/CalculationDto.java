package org.aitesting.microservices.tripmanagement.cmd.domain.models;

public class CalculationDto {
    private String origin;
    private String destination;
    private long departureTime;

    public CalculationDto() {
    }

    public CalculationDto(String origin, String destination, long departureTime) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
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

    @Override
    public String toString() {
        return String.format("origin: %s\ndestination: %s\ndepartureTime: %s\n",
                origin, destination, departureTime);
    }
}
