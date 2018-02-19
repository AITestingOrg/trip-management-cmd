package org.aitesting.microservices.tripmanagement.cmd.commands;

import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CompleteTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.StartTripCommand;
import org.aitesting.microservices.tripmanagement.common.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.TripCreatedEvent;
import org.aitesting.microservices.tripmanagement.common.TripStartedEvent;
import org.aitesting.microservices.tripmanagement.common.TripCompletedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.axonframework.test.aggregate.FixtureConfiguration;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandHandlerConfigurationTest {
    private FixtureConfiguration<Trip> fixture;
    private final String FROM_ADDRESS = "2250 north commerce parkway weston fl";
    private final String TO_ADDRESS = "Miami International Airport";

    @Before
    public void setUp() {
        fixture =  new AggregateTestFixture<>(Trip.class);
    }

    @Test
    public void createTrip() {
        UUID userID = UUID.randomUUID();
        CreateTripCommand command = new CreateTripCommand(userID, FROM_ADDRESS, TO_ADDRESS);
        fixture.given()
                .when(command)
                .expectEvents(new TripCreatedEvent(command.getId(), command.getUserId(), FROM_ADDRESS, TO_ADDRESS));
    }

    @Test
    public void cancelTrip() {
        UUID userID = UUID.randomUUID();
        CreateTripCommand createTripCommand = new CreateTripCommand(userID, FROM_ADDRESS, TO_ADDRESS);
        CancelTripCommand cancelTripCommand = new CancelTripCommand(createTripCommand.getId());

        fixture.givenCommands(createTripCommand)
                .when(cancelTripCommand)
                .expectEvents(new TripCanceledEvent(createTripCommand.getId()));
    }

    @Test
    public void cancelTripWhenNoValidTrip() {
        CancelTripCommand cancelTripCommand = new CancelTripCommand(UUID.randomUUID());

        fixture.given()
                .when(cancelTripCommand)
                .expectNoEvents();
    }

    @Test
    public void startTrip() {
        UUID userID = UUID.randomUUID();
        CreateTripCommand createTripCommand = new CreateTripCommand(userID, FROM_ADDRESS, TO_ADDRESS);
        StartTripCommand startTripCommand = new StartTripCommand(createTripCommand.getId());

        fixture.givenCommands(createTripCommand)
                .when(startTripCommand)
                .expectEvents(new TripStartedEvent(createTripCommand.getId()));
    }

    @Test
    public void startTripWhenNoValidTrip() {
        StartTripCommand startTripCommand = new StartTripCommand(UUID.randomUUID());

        fixture.given()
                .when(startTripCommand)
                .expectNoEvents();
    }

    @Test
    public void completeTrip() {
        UUID userID = UUID.randomUUID();
        CreateTripCommand createTripCommand = new CreateTripCommand(userID, FROM_ADDRESS, TO_ADDRESS);
        CompleteTripCommand completeTripCommand = new CompleteTripCommand(createTripCommand.getId());

        fixture.givenCommands(createTripCommand)
                .when(completeTripCommand)
                .expectEvents(new TripCompletedEvent(createTripCommand.getId()));
    }

    @Test
    public void completeTripWhenNoValidTrip() {
        CompleteTripCommand completeTripCommand = new CompleteTripCommand(UUID.randomUUID());

        fixture.given()
                .when(completeTripCommand)
                .expectNoEvents();
    }
}
