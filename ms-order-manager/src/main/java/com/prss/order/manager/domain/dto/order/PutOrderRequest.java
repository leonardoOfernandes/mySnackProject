package com.prss.order.manager.domain.dto.order;

import com.prss.order.manager.domain.dao.*;
import com.prss.order.manager.domain.dto.Domain;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PutOrderRequest implements Domain<Order> {
    private Integer id;
    private String note;

    @Override
    public Order toDomain() {
        return Order.builder()
                .id(id)
                .note(note)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
