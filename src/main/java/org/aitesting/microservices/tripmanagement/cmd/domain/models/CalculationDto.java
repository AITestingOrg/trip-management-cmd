package org.aitesting.microservices.tripmanagement.cmd.domain.models;

import java.util.Date;

public class CalculationDto {
    private String origin;
    private String destination;
    private Date departureTime;

    public CalculationDto() {
    }

    public CalculationDto(String origin, String destination, Date departureTime) {
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

    public Date getDeparture_time() {
        return departureTime;
    }
}
