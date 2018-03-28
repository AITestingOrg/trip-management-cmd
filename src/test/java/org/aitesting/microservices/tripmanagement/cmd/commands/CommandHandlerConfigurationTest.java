package org.aitesting.microservices.tripmanagement.cmd.commands;

import java.util.UUID;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.*;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.common.events.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest
public class CommandHandlerConfigurationTest {
    private FixtureConfiguration<Trip> fixture;
    private final String fromAddress = "2250 north commerce parkway weston fl";
    private final String toAddress = "Miami International Airport";
    private final UUID userId = UUID.randomUUID();

    @Before
    public void setUp() {
        fixture =  new AggregateTestFixture<>(Trip.class);
    }

    @Test
    public void createTrip() {
        CreateTripCommand command = new CreateTripCommand(userId, fromAddress, toAddress);
        fixture.given()
                .when(command)
                .expectEvents(new TripCreatedEvent(command.getId(), command.getUserId(), fromAddress, toAddress));
    }

    @Test
    public void updateTrip() {
        CreateTripCommand command = new CreateTripCommand(userId, fromAddress, toAddress);
        String newFromAddr = "New!";
        String newToAddr = "New Too!";
        UUID newUserId = UUID.randomUUID();
        fixture.given(command)
                .when(new UpdateTripCommand(command.getId(), new TripDto(newFromAddr, newToAddr, newUserId)))
                .expectEvents(new TripUpdatedEvent(command.getId(), command.getUserId(), newFromAddr, newToAddr, null));
    }

    @Test
    public void cancelTrip() {
        CreateTripCommand createTripCommand = new CreateTripCommand(userId, fromAddress, toAddress);
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
        CreateTripCommand createTripCommand = new CreateTripCommand(userId, fromAddress, toAddress);
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
        CreateTripCommand createTripCommand = new CreateTripCommand(userId, fromAddress, toAddress);
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
