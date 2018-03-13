package org.aitesting.microservices.tripmanagement.cmd.service.configuration;

import org.aist.libs.eventsourcing.configuration.configurations.AmqpEventPublisherConfiguration;
import org.aitesting.microservices.tripmanagement.cmd.domain.TripCommandHandler;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripEventStoreConfiguration
        extends AmqpEventPublisherConfiguration<Trip, TripCommandHandler> {
    public TripEventStoreConfiguration() {
        super(Trip.class);
    }

    @Override
    @Bean
    public TripCommandHandler commandHandler(
            EventSourcingRepository eventSourcingRepository, CommandBus commandBus) {
        TripCommandHandler commandHandler
                = new TripCommandHandler(eventSourcingRepository, commandBus);
        commandHandler.subscribe();
        return commandHandler;
    }
}
