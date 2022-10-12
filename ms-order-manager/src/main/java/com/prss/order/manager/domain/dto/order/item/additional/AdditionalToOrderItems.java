package com.prss.order.manager.domain.dto.order.item.additional;

import com.prss.order.manager.domain.dao.Additional;
import com.prss.order.manager.domain.dto.Domain;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AdditionalToOrderItems implements Domain<Additional> {
    private Integer idIngredient;
    private BigDecimal additionalAmount;

    @Override
    public Additional toDomain() {
        return Additional.builder()
                .additionalAmount(additionalAmount)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
