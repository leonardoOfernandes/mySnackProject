package com.prss.order.manager.resources.ordercommunicationstep.notifychatbot.notifiers;

import com.prss.order.manager.domain.enums.OrderStepEnum;
import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.messaging.event.impl.send.SendChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.ordercommunicationstep.model.OrderCommunicationStepData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderDeliveryAcceptedNotifier extends OrderCommunicationNotifier {

    @Autowired
    public OrderDeliveryAcceptedNotifier() {
        super(null);
    }

    @Override
    boolean isResponsible(final OrderCommunicationStepData data) {
        return data.getOrder().getOrderStep().equals(OrderStepEnum.ACCEPTED_BY_DELIVERYMAN.toString());
    }

    @Override
    SendChatbotWhatsappMessageEvent buildEmailEvent(final OrderCommunicationStepData data) {
        //TODO
        return SendChatbotWhatsappMessageEvent.builder().build();
    }
}
