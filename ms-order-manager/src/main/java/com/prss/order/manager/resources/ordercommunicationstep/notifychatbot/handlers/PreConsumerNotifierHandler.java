package com.prss.order.manager.resources.ordercommunicationstep.notifychatbot.handlers;

import com.prss.order.manager.resources.ordercommunicationstep.model.OrderCommunicationStepData;
import com.prss.order.manager.resources.ordercommunicationstep.notifychatbot.notifiers.OrderAcceptedNotifier;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */
@Component
public class PreConsumerNotifierHandler {

    @Autowired
    private OrderAcceptedNotifier notification;

    public void sendNotifications(OrderCommunicationStepData data) throws NotFoundException {
        notification.send(data);
    }
}
