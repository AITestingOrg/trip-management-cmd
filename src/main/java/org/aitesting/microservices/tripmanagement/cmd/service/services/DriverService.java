package org.aitesting.microservices.tripmanagement.cmd.service.services;

import org.aitesting.microservices.tripmanagement.cmd.domain.models.DriverDto;
import org.aitesting.microservices.tripmanagement.cmd.domain.models.Services;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService extends ApiService<DriverDto, DriverDto> {
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    public DriverService() {
        service = Services.DRIVERQUERY;
        port = 8080;
        type = DriverDto.class;
    }

    public DriverDto getAvailableDriver() {
        logger.info("Retrieving available driver");
        DriverDto availableDriver = getOne("api/v1/driver/available");
        return availableDriver;
    }
}
