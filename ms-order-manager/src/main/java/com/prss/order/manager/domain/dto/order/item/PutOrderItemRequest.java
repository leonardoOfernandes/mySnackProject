package com.prss.order.manager.domain.dto.order.item;

import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.Domain;
import com.prss.order.manager.domain.dto.order.item.additional.AdditionalToOrderItems;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PutOrderItemRequest implements Domain<OrderItems> {
    private Integer id;
    private BigDecimal totalAmount;
    private String note;
    private LocalDateTime updatedAt;
    private List<AdditionalToOrderItems> additionals;

    @Override
    public OrderItems toDomain() {
        return OrderItems.builder()
                .id(id)
                .note(note)
                .totalAmount(totalAmount)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
