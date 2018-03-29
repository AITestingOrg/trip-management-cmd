package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.util.Date;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.springframework.stereotype.Service;

@Service
public class CalculationService extends ApiService<TripInvoice, CalculationDto> {

    public CalculationService() {
        service = Services.CALCULATIONSERVICE;
    }

    public TripInvoice getInvoice(TripDto tripDto) {
        return create("cost", new CalculationDto(
                tripDto.getOriginAddress(),
                tripDto.getDestinationAddress(),
                new Date()));
    }
}
