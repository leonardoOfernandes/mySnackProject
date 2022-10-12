package com.prss.order.manager.domain.dto.orderdelivery;

import com.prss.order.manager.domain.dao.Deliveryman;
import com.prss.order.manager.domain.dao.Order;
import com.prss.order.manager.domain.dao.OrderDelivery;
import com.prss.order.manager.domain.dto.Domain;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostOrderDeliveryRequest implements Domain<OrderDelivery> {
    private Integer idOrder;
    private Integer idDeliveryman;
    private BigDecimal deliveryAmount;
    private Instant estimate;

    @Override
    public OrderDelivery toDomain() {
        return OrderDelivery.builder()
                .deliveryAmmount(deliveryAmount)
                .deliveryAccepted(true)
                .estimate(estimate)
                .deliveryDate(null)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
