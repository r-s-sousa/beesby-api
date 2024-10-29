package com.beesby.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthRequest {

    @NotNull(message = "CPF is required")
    @Size(min = 11, max = 11, message = "CPF must be exactly 11 characters")
    @JsonProperty("cpf")
    private String cpf;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 30, message = "Name must be between 3 and 255 characters")
    @JsonProperty("password")
    private String password;
}
