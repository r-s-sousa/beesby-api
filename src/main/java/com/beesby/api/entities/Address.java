package com.beesby.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(length = 255)
    private String street;

    @Column(length = 10)
    private String number;

    @Column(length = 255)
    private String complement;

    @Column(length = 255)
    private String neighborhood;

    @Column(length = 255)
    private String city;

    @Size(min = 2, max = 2, message = "State must be a 2-letter code")
    @Column(length = 2)
    private String state;

    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Zip code must follow the format 12345-678 or 12345678")
    @Column(length = 8)
    private String zipCode;
}
