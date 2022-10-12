package com.prss.login.domain.dto.restaurant;

import com.prss.registers.application.config.Regex;
import com.prss.registers.domain.dao.Restaurant;
import com.prss.registers.domain.dto.Domain;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostRestaurantRequest implements Domain<Restaurant> {
    @Pattern(regexp = Regex.FULL_NAME, message = "at least two words with at least two letters each")
    private String name;
    @Pattern(regexp = Regex.DOCUMENT_NUMBER, message = "document number must has 11 digits")
    private String documentNumber;
    private String password;
    @Pattern(regexp = Regex.POSTAL_CODE, message = "invalid postal code")
    private String postalCode;
    private String streetName;
    private String neighborhood;
    private String city;
    private String type;
    private String phoneNumber;
    @Pattern(regexp = Regex.EMAIL, message = "invalid email")
    private String email;



    @Override
    public Restaurant toDomain() {
        return Restaurant.builder()
                .name(name)
                .documentNumber(documentNumber)
                .password(password)
                .postalCode(Integer.valueOf(postalCode))
                .streetName(streetName)
                .neighborhood(neighborhood)
                .city(city)
                .type(type)
                .phoneNumber(phoneNumber)
                .email(email)
                .createdAt(LocalDateTime.now())
                .build();

    }
}
