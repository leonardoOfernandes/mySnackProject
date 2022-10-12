package com.prss.order.manager.domain.dto.order.item;

import com.prss.order.manager.domain.dao.MenuItems;
import com.prss.order.manager.domain.dao.Order;
import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.Domain;
import com.prss.order.manager.domain.dto.order.item.additional.AdditionalToOrderItems;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemsToOrder implements Domain<OrderItems> {

    private Integer idItem;
    private BigDecimal totalAmount;
    private String note;
    private LocalDateTime createdAt;
    private List<AdditionalToOrderItems> additionals;
    @Override
    public OrderItems toDomain() {
        return OrderItems.builder()
                .totalAmount(totalAmount)
                .note(note)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
