package com.prss.login.domain.dto.ingredient;

import com.prss.registers.domain.dao.Ingredients;
import com.prss.registers.domain.dto.Domain;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostIngredientRequest  implements Domain<Ingredients> {
    private String description;
    private BigDecimal amountPaid;

    @Override
    public Ingredients toDomain() {
        return Ingredients.builder()
                .description(description)
                .amountPaid(amountPaid)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
