package com.beesby.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    @Size(max = 255, message = "Street must not exceed 255 characters")
    @JsonProperty("street")
    private String street;

    @Size(max = 10, message = "Number must not exceed 10 characters")
    @JsonProperty("number")
    private String number;

    @JsonProperty("complement")
    private String complement;

    @Size(max = 255, message = "Neighborhood must not exceed 255 characters")
    @JsonProperty("neighborhood")
    private String neighborhood;

    @Size(max = 255, message = "City must not exceed 255 characters")
    @JsonProperty("city")
    private String city;

    @Size(max = 2, message = "State must be exactly 2 characters")
    @JsonProperty("state")
    private String state;

    @Size(max = 8, message = "Zip code must not exceed 8 characters")
    @JsonProperty("zip_code")
    private String zipCode;
}