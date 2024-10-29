package com.beesby.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("address")
    private AddressResponse address;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    private UUID createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_by")
    private UUID updatedBy;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("deleted_by")
    private UUID deletedBy;
}
