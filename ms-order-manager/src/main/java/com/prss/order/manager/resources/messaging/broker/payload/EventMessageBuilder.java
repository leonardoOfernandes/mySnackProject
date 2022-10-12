package com.prss.order.manager.resources.messaging.broker.payload;

import com.prss.order.manager.resources.messaging.event.Event;
import com.prss.order.manager.resources.messaging.event.MessageBody;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

public final class EventMessageBuilder {

    private EventMessageBuilder(){}

    public static MessageBuilder<Event> withEventSourceBuilder(final Event eventSourcing, Map<String, String> headers){
        return MessageBuilder.withPayload(eventSourcing).copyHeaders(headers);
    }

    public static MessageBuilder<MessageBody> withMessageBodyBuilder(final MessageBody messageBody, Map<String, String> headers){
        return MessageBuilder.withPayload(messageBody).copyHeaders(headers);
    }
}
