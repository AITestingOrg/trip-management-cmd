package org.aitesting.microservices.tripmanagement.cmd.models;

import java.util.Date;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.junit.Assert;
import org.junit.Test;

public class CalculationDtoTest {
    private final String origin = "from";
    private final String destination = "to";
    private final long depart = new Date().getTime();

    @Test
    public void createdCalculationDtoHasCorrectValues() {
        //given
        CalculationDto calculation = new CalculationDto(origin, destination, depart);

        //assert
        Assert.assertEquals(calculation.getOrigin(), origin);
        Assert.assertEquals(calculation.getDestination(), destination);
        Assert.assertEquals(calculation.getDeparture_time(), depart);
    }
}
