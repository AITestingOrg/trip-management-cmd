package org.aitesting.microservices.tripmanagement.cmd.models;

import java.util.UUID;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.junit.Assert;
import org.junit.Test;


public class TripDtoTest {
    private final String from = "from";
    private final String to = "to";
    private final UUID userId = UUID.randomUUID();

    @Test
    public void createdTripDtoHasCorrectValues() {
        //given
        TripDto trip = new TripDto(from, to, userId);

        //assert
        Assert.assertEquals(trip.getOriginAddress(), from);
        Assert.assertEquals(trip.getDestinationAddress(), to);
        Assert.assertEquals(trip.getUserId(), userId);
    }
}
