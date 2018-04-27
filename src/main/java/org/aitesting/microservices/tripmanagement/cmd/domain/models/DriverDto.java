package org.aitesting.microservices.tripmanagement.cmd.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonIgnoreProperties (ignoreUnknown=true)
public class DriverDto {
    @NotNull
    private UUID id;
    private String fname;
    private String lname;
    private String address;
    private String email;
    private String phone;
    private String license;

    private double current_location_lat;
    private double current_location_long;
    private boolean available;
    private double rating;
    private int numberOfRatings;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLicense() {
        return license;
    }

    public double getCurrent_location_lat() {
        return current_location_lat;
    }

    public double getCurrent_location_long() {
        return current_location_long;
    }

    public boolean isAvailable() {
        return available;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public DriverDto(UUID id, String fname, String lname, String address, String email, String phone, String license) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.license = license;
        this.current_location_lat = 0;
        this.current_location_long = 0;
        this.available = false;
        this.rating = 0;
        this.numberOfRatings = 0;
    }

    public DriverDto() {}

    public UUID getId() {
        return id;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
