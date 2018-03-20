package org.aitesting.microservices.tripmanagement.cmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TripManagementCmdApplication {
    public static void main(String[] args) {
        SpringApplication.run(TripManagementCmdApplication.class, args);
    }
}
