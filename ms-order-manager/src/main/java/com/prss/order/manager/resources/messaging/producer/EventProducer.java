package com.prss.order.manager.resources.messaging.producer;


import com.prss.order.manager.resources.messaging.broker.payload.EventMessageBuilder;
import com.prss.order.manager.resources.messaging.broker.payload.KafkaMessage;
import com.prss.order.manager.resources.messaging.broker.payload.MessageHeaderPreConsumer;
import com.prss.order.manager.resources.messaging.event.Event;
import com.prss.order.manager.resources.messaging.event.EventStrategy;
import com.prss.order.manager.resources.messaging.event.MessageBody;
import com.prss.order.manager.resources.messaging.exception.EventTopicProducerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class EventProducer {

    @Autowired
    private EventStrategy eventStrategy;

    public void sendMessage(KafkaMessage kafkaMessage) {
        try {
            final Event event = kafkaMessage.getSourceEvent();
            final MessageChannel messageChannel = eventStrategy.getEventMessage(event);

            Map<String, String> messageHeaders = MessageHeaderPreConsumer
                .buildMapToConsumer(event.getEventType(), kafkaMessage);

            MessageBuilder<Event> messageBuilder = EventMessageBuilder.withEventSourceBuilder(event, messageHeaders);

            log.info("[send-message] Sending message to topic {} with correlationId {}", event.getEventProducer(), kafkaMessage.getCorrelationId());

            messageChannel.send(messageBuilder.build());
        } catch (Exception e) {
            log.error("[send-message] - error sending message to kafka topic: " + kafkaMessage.getSourceEvent().getEventProducer());
            throw new EventTopicProducerException("[send-message] - error sending message to kafka topic: " + kafkaMessage.getSourceEvent().getEventProducer(), e);
        }
    }

    public void sendMessageBody(KafkaMessage kafkaMessage) {
        try {
            final Event eventSource = kafkaMessage.getSourceEvent();
            final MessageChannel messageChannel = eventStrategy.getEventMessage(eventSource);

            MessageBody messageBody = MessageBody.builder()
                .event(eventSource.getEventType())
                .source(eventSource)
                .build();

            Map<String, String> messageHeaders = MessageHeaderPreConsumer
                .buildMapToConsumer(
                    eventSource.getEventType(),
                    kafkaMessage);

            MessageBuilder<MessageBody> messageBuilder = EventMessageBuilder.withMessageBodyBuilder(messageBody, messageHeaders);

            log.info("[send-message] Sending message to topic {} with correlationId {}", eventSource.getEventProducer(), kafkaMessage.getCorrelationId());

            messageChannel.send(messageBuilder.build());
        } catch (Exception e) {
            throw new EventTopicProducerException("[send-message] error sending message to kafka topic", e);
        }
    }
}
