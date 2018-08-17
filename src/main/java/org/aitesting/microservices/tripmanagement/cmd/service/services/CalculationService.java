package org.aitesting.microservices.tripmanagement.cmd.service.services;

import java.util.Date;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculationService extends ApiService<TripInvoice, CalculationDto> {
    private static final Logger logger = LoggerFactory.getLogger(CalculationService.class);

    public CalculationService() {
        service = Services.CALCULATIONSERVICE;
        port = 8080;
        type = TripInvoice.class;
    }

    public TripInvoice getInvoice(TripDto tripDto) {
        logger.info("Request to calculationservice for invoice");
        TripInvoice reply = create("api/v1/cost", new CalculationDto(
                tripDto.getOriginAddress(),
                tripDto.getDestinationAddress(),
                new Date().getTime(),
                tripDto.getUserId()));
        logger.info(String.format("Trip invoice received:\nCost: %s\nLastUpdated %s",
                reply.getCost(), reply.getLastUpdated()));
        return reply;
    }
}
