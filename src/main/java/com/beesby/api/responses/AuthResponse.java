package com.beesby.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("expires_in")
    private long expiresIn;
}
