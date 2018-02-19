package org.aitesting.microservices.tripmanagement.cmd.service.configuration;

import org.aist.libs.eventsourcing.configuration.configurations.AmqpEventSubscriptionConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripAmqpEventConfiguration extends AmqpEventSubscriptionConfiguration{
}
