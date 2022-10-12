package com.prss.order.manager.resources.ordercommunicationstep;

import com.prss.order.manager.resources.ordercommunicationstep.model.OrderCommunicationStepData;
import com.prss.order.manager.resources.ordercommunicationstep.notifychatbot.handlers.PreConsumerNotifierHandler;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Slf4j
@Component
public class OrderCommunicationNotifyHandler {


    @Autowired
    private PreConsumerNotifierHandler preConsumerNotifierHandler;

    public void handle(OrderCommunicationStepData data) throws NotFoundException {
        preConsumerNotifierHandler.sendNotifications(data);
    }
}