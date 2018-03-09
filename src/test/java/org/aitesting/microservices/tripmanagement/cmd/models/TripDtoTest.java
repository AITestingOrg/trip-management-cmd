package org.aitesting.microservices.tripmanagement.cmd.models;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TripDtoTest {
    private final String FROM = "from";
    private final String TO = "to";
    private final UUID USER_ID = UUID.randomUUID();

    @Test
    public void createdTripDtoHasCorrectValues() {
        //given
        TripDto trip = new TripDto(FROM, TO, USER_ID);

        //assert
        Assert.assertEquals(trip.getOriginAddress(), FROM);
        Assert.assertEquals(trip.getDestinationAddress(), TO);
        Assert.assertEquals(trip.getUserId(), USER_ID);
    }
}
