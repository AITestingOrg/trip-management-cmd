package org.aitesting.microservices.tripmanagement.cmd.domain.events;

import org.aitesting.microservices.tripmanagement.cmd.domain.commands.EstimateTripCommand;
import org.aitesting.microservices.tripmanagement.common.events.TripCreatedEvent;
import org.aitesting.microservices.tripmanagement.common.events.TripUpdatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripEventHandler {
    protected static final Logger LOG = LoggerFactory.getLogger(TripEventHandler.class);

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(TripCreatedEvent event) {
        LOG.trace("Created Trip: {}", event.getId());
        EstimateTripCommand command = new EstimateTripCommand(event.getId(),
                event.getOriginAddress(),
                event.getDestinationAddress());
        commandGateway.send(command);
        LOG.info("Estimate Trip Command Dispatched {}.", command.getId());
    }

    @EventHandler
    public void on(TripUpdatedEvent event) {
        LOG.trace("Created Trip: {}", event.getId());
        EstimateTripCommand command = new EstimateTripCommand(event.getId(),
                event.getOriginAddress(),
                event.getDestinationAddress());
        commandGateway.send(command);
        LOG.info("Estimate Trip Command Dispatched {}.", command.getId());
    }
}
