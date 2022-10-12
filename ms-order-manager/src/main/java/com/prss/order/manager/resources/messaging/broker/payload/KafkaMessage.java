package com.prss.order.manager.resources.messaging.broker.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.prss.order.manager.resources.messaging.event.Event;
import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import static java.util.UUID.randomUUID;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage {

    private static final String CORRELATION_ID = "correlation_id";
    public static final String SPRING_APPLICATION_NAME = "spring.application.name";

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "event")
    @JsonSubTypes(value = {
        @JsonSubTypes.Type(value = ReceiveChatbotWhatsappMessageEvent.class, name = ReceiveChatbotWhatsappMessageEvent.RECEIVE_WHATSAPP_MESSAGE)
    })
    private Event sourceEvent;

    private String correlationId;

    public static KafkaMessage build(Event event) {
        String correlationId = isNotBlank(MDC.get(CORRELATION_ID)) ? MDC.get(CORRELATION_ID) : randomUUID().toString();
        return KafkaMessage.builder()
            .sourceEvent(event)
            .correlationId(correlationId)
            .build();
    }
}
