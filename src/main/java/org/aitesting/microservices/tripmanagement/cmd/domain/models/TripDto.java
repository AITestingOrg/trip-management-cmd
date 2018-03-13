package org.aitesting.microservices.tripmanagement.cmd.domain.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TripDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private String originAddress;
    @NotNull
    @NotEmpty
    @NotBlank
    private String destinationAddress;
    @NotNull
    private UUID userId;

    public TripDto() {
    }

    public TripDto(String originAddress, String destinationAddress, UUID userId) {
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.userId = userId;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
