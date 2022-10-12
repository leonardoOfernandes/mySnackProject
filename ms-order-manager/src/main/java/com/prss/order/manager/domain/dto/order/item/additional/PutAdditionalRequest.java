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
public class PutAdditionalRequest implements Domain<Additional> {
    private Integer id;
    private BigDecimal additionalAmount;

    @Override
    public Additional toDomain() {
        return Additional.builder()
                .id(id)
                .additionalAmount(additionalAmount)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
