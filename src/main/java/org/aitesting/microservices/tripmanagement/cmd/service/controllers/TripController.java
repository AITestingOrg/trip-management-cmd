package org.aitesting.microservices.tripmanagement.cmd.service.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CompleteTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
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

    @PostMapping("trip")
    public ResponseEntity<Map<String, Object>> addTrip(@Valid @RequestBody TripDto trip) {
        logger.info(String.format("Request to add trip Origin: %s, Destination: %s, UserId: %s", trip.getOriginAddress(), trip.getDestinationAddress(), trip.getUserId()));
        CreateTripCommand createTripCommand = new CreateTripCommand(
                trip.getUserId(), trip.getOriginAddress(), trip.getDestinationAddress());
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

    @PutMapping("trip/completed/{id}")
    public void completeTrip(@PathVariable("id") UUID id) {
        logger.info(String.format("Request to complete trip %s", id));
        commandGateway.send(new CompleteTripCommand(id));
        logger.trace(String.format("Dispatched CompleteTripCommand %s", id));
    }
}
