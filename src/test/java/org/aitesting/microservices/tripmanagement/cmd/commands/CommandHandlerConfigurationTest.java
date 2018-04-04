package org.aitesting.microservices.tripmanagement.cmd.commands;

import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.UUID;
import org.aitesting.microservices.tripmanagement.cmd.domain.aggregates.TripAggregate;
import org.aitesting.microservices.tripmanagement.cmd.domain.commands.*;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.common.events.*;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest
@ContextConfiguration
public class CommandHandlerConfigurationTest {
    private FixtureConfiguration<TripAggregate> fixture;
    private final String fromAddress = "2250 north commerce parkway weston fl";
    private final String toAddress = "Miami International Airport";
    private final UUID userId = UUID.randomUUID();

    @Configuration
    static class TestConfig {
        private RestTemplate restTemplate = mock(RestTemplate.class);

        public static final TripInvoice tripInvoice = new TripInvoice(
                "1223 Test",
                "321 Test",
                4,
                10,
                30,
                new Date());

        @Bean
        public RestTemplate restTemplate() {
            // Calculation service mock
            ResponseEntity<TripInvoice> response = new ResponseEntity<>(tripInvoice, HttpStatus.OK);
            Mockito.when(restTemplate.exchange(
                    Matchers.any(String.class),
                    Matchers.same(HttpMethod.POST),
                    Matchers.<HttpEntity<?>>any(),
                    Matchers.<Class<TripInvoice>>any(),
                    Matchers.<Class<CalculationDto>>any()))
                    .thenReturn(response);
            return restTemplate;
        }
    }

    @Before
    public void setUp() {
        fixture =  new AggregateTestFixture<>(TripAggregate.class);
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
        String newFromAddr = "New!";
        String newToAddr = "New Too!";
        UUID newUserId = UUID.randomUUID();
        CreateTripCommand createCommand = new CreateTripCommand(userId, fromAddress, toAddress);
        UpdateTripCommand updateCommand = new UpdateTripCommand(createCommand.getId(),
                new TripDto(newFromAddr, newToAddr, newUserId));
        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectEvents(new TripUpdatedEvent(createCommand.getId(), createCommand.getUserId(),
                        newFromAddr, newToAddr, null));
    }

    @Test
    public void estimateTrip() {
        TripInvoice tripInvoice = new TripInvoice(fromAddress, toAddress, 10, 5, 30, new Date());
        CreateTripCommand createCommand = new CreateTripCommand(userId, fromAddress, toAddress);
        EstimateTripCommand updateCommand = new EstimateTripCommand(createCommand.getId(),
                new TripDto(fromAddress, toAddress, userId, tripInvoice));
        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectEvents(new TripEstimatedEvent(createCommand.getId(), updateCommand.getInvoice()));
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
