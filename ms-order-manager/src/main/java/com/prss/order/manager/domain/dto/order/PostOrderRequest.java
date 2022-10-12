package com.prss.order.manager.domain.dto.order;

import com.prss.order.manager.domain.dao.*;
import com.prss.order.manager.domain.dto.Domain;
import com.prss.order.manager.domain.dto.order.item.OrderItemsToOrder;
import com.prss.order.manager.domain.enums.OrderStepEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostOrderRequest implements Domain<Order> {
    private Integer idRestaurant;
    private Integer idClient;
    private BigDecimal totalAmount;
    private String note;
    private List<OrderItemsToOrder> orderItems;

    @Override
    public Order toDomain() {
        return Order.builder()
                .totalAmount(totalAmount)
                .note(note)
                .orderStep(OrderStepEnum.DONE.toString())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
