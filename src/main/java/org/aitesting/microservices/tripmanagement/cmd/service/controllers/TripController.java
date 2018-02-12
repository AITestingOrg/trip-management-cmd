package org.aitesting.microservices.tripmanagement.cmd.service.controllers;

import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api")
public class TripController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("trip")
    public void addTrip(@RequestBody TripDto trip){
        commandGateway.send(
                new CreateTripCommand(trip.getUserId(), trip.getOriginAddress(), trip.getDestinationAddress()));
    }

    @PutMapping("trip/cancel/{id}")
    public void cancelTrip(@PathVariable("id") UUID id){
        commandGateway.send(new CancelTripCommand(id));
    }
}
