package com.prss.order.manager.domain.dto.order.item.additional;

import com.prss.order.manager.domain.dao.Additional;
import com.prss.order.manager.domain.dao.Ingredients;
import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.Domain;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostAdditionalRequest implements Domain<Additional> {
    private Integer idIngredient;
    private Integer idOrderitem;
    private BigDecimal additionalAmount;
    private LocalDateTime createdAt;

    @Override
    public Additional toDomain() {
        return Additional.builder()
                .additionalAmount(additionalAmount)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
