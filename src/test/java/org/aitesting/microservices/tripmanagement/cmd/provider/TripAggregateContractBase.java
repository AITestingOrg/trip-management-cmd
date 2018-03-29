package org.aitesting.microservices.tripmanagement.cmd.provider;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.aitesting.microservices.tripmanagement.cmd.TripManagementCmdApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@Profile("test")
@SpringBootTest(classes = TripManagementCmdApplication.class)
public abstract class TripAggregateContractBase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @After
    public void teardown() {
    }
}
