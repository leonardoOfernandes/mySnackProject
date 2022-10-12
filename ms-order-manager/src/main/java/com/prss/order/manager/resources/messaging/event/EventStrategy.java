package com.prss.order.manager.resources.messaging.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventStrategy {

    @Autowired
    private Map<String, MessageChannel> messages;

    public MessageChannel getEventMessage(Event event) {
        return messages.get(event.getEventProducer());
    }
}
