package com.prss.order.manager.resources.ordercommunicationstep.model;

import com.prss.order.manager.domain.dao.Order;
import com.prss.order.manager.domain.dao.OrderDelivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommunicationStepData {
    private Order order;
    private OrderDelivery orderDelivery;
}
