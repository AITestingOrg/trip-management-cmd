package org.aitesting.microservices.tripmanagement.cmd.services;

import static org.aitesting.microservices.tripmanagement.cmd.configuration.TestConstants.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.CalculationDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.cmd.service.services.CalculationService;
import org.aitesting.microservices.tripmanagement.common.models.TripInvoice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceUnitTest {

    @InjectMocks
    private CalculationService calculationService = new CalculationService();

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInvoiceSuccess() throws Exception {
        //arrange
        TripDto tripDto = new TripDto(FROM, TO, TRIP_ID1);
        CalculationDto calculationDto = new CalculationDto(FROM, TO, new Date().getTime());
        TripInvoice invoice = new TripInvoice(
                calculationDto.getOrigin(),
                calculationDto.getDestination(),
                12.43,
                10,
                23.43,
                new Date());

        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(invoice);
        ResponseEntity<String> response = new ResponseEntity<>(input, HttpStatus.OK);

        doReturn(response).when(restTemplate).exchange(
                    ArgumentMatchers.any(String.class),
                    ArgumentMatchers.same(HttpMethod.POST),
                    ArgumentMatchers.<HttpEntity<?>>any(),
                    ArgumentMatchers.<Class<String>>any());

        //act
        TripInvoice reply = calculationService.getInvoice(tripDto);

        //assert
        Assert.assertEquals(reply.getOriginAddress(), FROM);
        Assert.assertEquals(reply.getDestinationAddress(), TO);
        Assert.assertEquals(reply.getDuration(), invoice.getDuration());

    }
}
