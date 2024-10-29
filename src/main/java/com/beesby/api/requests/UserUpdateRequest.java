package com.beesby.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {

    @Size(min = 11, max = 11, message = "CPF must be exactly 11 characters")
    @JsonProperty("cpf")
    private String cpf;

    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @JsonProperty("name")
    private String name;

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @JsonProperty("password")
    private String password;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("address")
    private AddressRequest address;
}