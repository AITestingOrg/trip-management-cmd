package org.aitesting.microservices.tripmanagement.cmd.provider;

import static org.aitesting.microservices.tripmanagement.cmd.configuration.TestConstants.INVOICE;
import static org.mockito.BDDMockito.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.aitesting.microservices.tripmanagement.cmd.TripManagementCmdApplication;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.TripDto;
import org.aitesting.microservices.tripmanagement.cmd.service.services.CalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = TripManagementCmdApplication.class)
public abstract class TripAggregateContractBase {

    @MockBean
    CalculationService calculationService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        given(this.calculationService.getInvoice(Mockito.any(TripDto.class)))
                .willReturn(INVOICE);
    }

    @After
    public void teardown() {
    }
}
