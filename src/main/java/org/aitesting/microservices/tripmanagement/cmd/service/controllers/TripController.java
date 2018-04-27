package org.aitesting.microservices.tripmanagement.cmd.service.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.*;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.cmd.service.services.CalculationService;
import org.aitesting.microservices.tripmanagement.cmd.service.services.DriverService;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    CalculationService calculationService;

    @Autowired
    DriverService driverService;

    @PostMapping("trip")
    public ResponseEntity<Map<String, Object>> addTrip(@Valid @RequestBody TripDto trip) {
        logger.info(String.format("Request to add trip Origin: %s, Destination: %s, UserId: %s",
                trip.getOriginAddress(),
                trip.getDestinationAddress(),
                trip.getUserId()));
        TripInvoice invoice = calculationService.getInvoice(trip);
        CreateTripCommand createTripCommand = new CreateTripCommand(
                trip.getUserId(), trip.getOriginAddress(), trip.getDestinationAddress(), invoice);
        commandGateway.send(createTripCommand);
        logger.trace(String.format("Dispatched CreateTripCommand %s", createTripCommand.getId()));
        Map<String, Object> json = new HashMap<>();
        json.put("id", createTripCommand.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        return (new ResponseEntity<>(json, headers, HttpStatus.CREATED));
    }

    @PutMapping("trip/cancel/{id}")
    public void cancelTrip(@PathVariable("id") UUID id) {
        logger.info(String.format("Request to cancel trip %s", id));
        commandGateway.send(new CancelTripCommand(id));
        logger.trace(String.format("Dispatched CancelTripCommand %s", id));
    }

    @PutMapping("trip/start/{id}")
    public void startTrip(@PathVariable("id") UUID id) {
        logger.info(String.format("Request to start trip %s", id));
        commandGateway.send(new StartTripCommand(id));
        logger.trace(String.format("Dispatched StartTripCommand %s", id));
    }

    @PutMapping("trip/accept/{id}")
    public void acceptTrip(@PathVariable("id") UUID id) {
        logger.info(String.format("Request to accept trip %s", id));
        UUID driverId = driverService.getAvailableDriver().getId();
        commandGateway.send(new AcceptTripCommand(id, driverId));
        logger.trace(String.format("Dispatched AcceptTripCommand %s", id));
    }

    @PutMapping("trip/completed/{id}")
    public void completeTrip(@PathVariable("id") UUID id) {
        logger.info(String.format("Request to complete trip %s", id));
        commandGateway.send(new CompleteTripCommand(id));
        logger.trace(String.format("Dispatched CompleteTripCommand %s", id));
    }

    @PutMapping("trip/update/{id}")
    public void updateTrip(@PathVariable("id") UUID id, @RequestBody TripDto trip) throws Exception {
        logger.info(String.format("Request to update trip %s", id));
        // todo: move this logic to a service
        try {
            // this is done outside of the aggregate to avoid blocking it.
            TripInvoice invoiceEstimate = calculationService.getInvoice(trip);
            trip.setEstimate(invoiceEstimate);
            commandGateway.send(new UpdateTripCommand(id, trip));
            logger.trace(String.format("Dispatched UpdateTripCommand %s", id));
        } catch (Exception e) {
            throw new Exception("Oops, something went wrong. Please try again later.");
        }
    }
}
