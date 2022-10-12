package com.prss.order.manager.resources.ordercommunicationstep.notifychatbot.notifiers;

import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.messaging.event.impl.send.SendChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.ordercommunicationstep.model.OrderCommunicationStepData;
import com.prss.order.manager.resources.service.messaging.NotificationService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import static java.util.Objects.nonNull;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */
@Slf4j
public abstract class OrderCommunicationNotifier {
    @Autowired
    protected NotificationService notificationService;

    private final OrderCommunicationNotifier next;

    protected OrderCommunicationNotifier(OrderCommunicationNotifier next) {
        this.next = next;
    }

    public void send(OrderCommunicationStepData data) throws NotFoundException {
        if (isResponsible(data)) {
            SendChatbotWhatsappMessageEvent emailEvent = buildEmailEvent(data);

            notificationService.sendNotification(emailEvent);
        } else if (nonNull(next)) {
            next.send(data);
        } else {
            log.error("notifier not hound to pre consumer data: {}", data);
            throw new NotFoundException("notifier not hound to pre consumer data: " + data);
        }
    }

    abstract boolean isResponsible(OrderCommunicationStepData data);

    abstract SendChatbotWhatsappMessageEvent buildEmailEvent(OrderCommunicationStepData data);
}
