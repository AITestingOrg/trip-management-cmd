package org.aitesting.microservices.tripmanagement.cmd.service.configuration;

import org.aist.libs.eventsourcing.configuration.configurations.AmqpEventPublisherConfiguration;
import org.aitesting.microservices.tripmanagement.cmd.domain.TripCommandHandler;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.TripAggregate;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TripEventStoreConfiguration
        extends AmqpEventPublisherConfiguration<TripAggregate, TripCommandHandler> {

    public TripEventStoreConfiguration() {
        super(TripAggregate.class);
    }

    @Bean
    public TripCommandHandler commandHandler(
            EventSourcingRepository eventSourcingRepository, CommandBus commandBus) {
        TripCommandHandler commandHandler
                = new TripCommandHandler(eventSourcingRepository, commandBus);
        commandHandler.subscribe();
        return commandHandler;
    }
}
