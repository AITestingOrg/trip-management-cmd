package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.net.URISyntaxException;
import java.util.Date;

import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.springframework.stereotype.Service;

@Service
public class CalculationService extends ApiService<TripInvoice, CalculationDto> {

    public CalculationService() {
        service = Services.CALCULATION_SERVICE;
    }

    public TripInvoice getInvoice(Trip trip) throws URISyntaxException {
        return create("cost", new CalculationDto(trip.getOriginAddress(), trip.getDestinationAddress(), new Date()));
    }
}
