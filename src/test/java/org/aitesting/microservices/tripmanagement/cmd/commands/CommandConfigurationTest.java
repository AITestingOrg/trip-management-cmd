package org.aitesting.microservices.tripmanagement.cmd.commands;

import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.Trip;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CancelTripCommand;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.CreateTripCommand;
import org.aitesting.microservices.tripmanagement.common.TripCanceledEvent;
import org.aitesting.microservices.tripmanagement.common.TripCreatedEvent;
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
public class CommandConfigurationTest {
    private FixtureConfiguration<Trip> fixture;

    @Before
    public void setUp() throws Exception {
        fixture =  new AggregateTestFixture<>(Trip.class);
    }

    @Test
    public void createTrip(){
        UUID userID = UUID.randomUUID();
        String fromAddress = "2250 north commerce parkway weston fl";
        String toAddress = "Miami International Airport";
        CreateTripCommand command = new CreateTripCommand(userID, fromAddress, toAddress);
        fixture.given()
                .when(command)
                .expectEvents(new TripCreatedEvent(command.getId(), command.getUserId(), fromAddress, toAddress));
    }

    @Test
    public void cancelTrip(){
        UUID userID = UUID.randomUUID();
        String fromAddress = "2250 north commerce parkway weston fl";
        String toAddress = "Miami International Airport";
        CreateTripCommand createTripCommand = new CreateTripCommand(userID, fromAddress, toAddress);
        CancelTripCommand cancelTripCommand = new CancelTripCommand(createTripCommand.getId());

        fixture.givenCommands(createTripCommand)
                .when(cancelTripCommand)
                .expectEvents(new TripCanceledEvent(createTripCommand.getId()));
    }
}
