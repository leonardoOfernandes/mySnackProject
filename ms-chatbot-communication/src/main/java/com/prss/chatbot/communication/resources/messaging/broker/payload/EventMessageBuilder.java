package com.prss.chatbot.communication.resources.messaging.broker.payload;

import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import com.picpay.cardpreconsumer.resources.messaging.event.MessageBody;
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
