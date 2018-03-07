package org.aitesting.microservices.tripmanagement.cmd.service.controllers;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CompleteTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class TripController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("trip")
    public ResponseEntity<Map<String, Object>> addTrip(@RequestBody TripDto trip){
        CreateTripCommand createTripCommand = new CreateTripCommand(trip.getUserId(), trip.getOriginAddress(), trip.getDestinationAddress());
        commandGateway.send(createTripCommand);
        Map<String, Object> json = new HashMap<>();
        json.put("success", true);
        json.put("id", createTripCommand.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");

        return (new ResponseEntity<>(json, headers, HttpStatus.OK));
    }

    @PutMapping("trip/cancel/{id}")
    public void cancelTrip(@PathVariable("id") UUID id){
        commandGateway.send(new CancelTripCommand(id));
    }

    @PutMapping("trip/start/{id}")
    public void startTrip(@PathVariable("id") UUID id){
        commandGateway.send(new StartTripCommand(id));
    }

    @PutMapping("trip/completed/{id}")
    public void completeTrip(@PathVariable("id") UUID id){
        commandGateway.send(new CompleteTripCommand(id));
    }
}
