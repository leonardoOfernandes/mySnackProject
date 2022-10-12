package com.prss.order.manager.domain.dto.order.item;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostOrderItemRequest {
    private Integer idOrder;
    private List<OrderItemsToOrder> orderItem;
}
