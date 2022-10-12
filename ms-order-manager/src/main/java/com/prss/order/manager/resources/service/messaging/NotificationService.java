package com.prss.order.manager.resources.service.messaging;

import com.prss.order.manager.resources.messaging.broker.payload.KafkaMessage;
import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.messaging.event.impl.send.SendChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.messaging.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EventProducer eventProducer;

    public void sendNotification(SendChatbotWhatsappMessageEvent emailEvent) {
        eventProducer.sendMessage(KafkaMessage.build(emailEvent));
    }
}
