package com.prss.registers.domain.dto.ingredient;

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
public class PutIngredientRequest implements Domain<Ingredients> {
    private Integer id;
    private String description;
    private BigDecimal amountPaid;

    @Override
    public Ingredients toDomain() {
        return Ingredients.builder()
                .id(id)
                .description(description)
                .amountPaid(amountPaid)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
