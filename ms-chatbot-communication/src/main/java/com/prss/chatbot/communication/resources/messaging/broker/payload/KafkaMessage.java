package com.prss.chatbot.communication.resources.messaging.broker.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.CreditAnalysisResultEvent;
import com.picpay.cardpreconsumer.resources.security.wrapper.utils.EnvAccessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage {

    private static final String CORRELATION_ID = "correlation_id";
    public static final String SPRING_APPLICATION_NAME = "spring.application.name";

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "event")
    @JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CreditAnalysisResultEvent.class, name = CreditAnalysisResultEvent.LIMIT_ANALYSIS_RESULT)
    })
    private Event sourceEvent;

    private String applicationName;

    private String correlationId;

    public static KafkaMessage build(Event event) {
        String correlationId = isNotBlank(MDC.get(CORRELATION_ID)) ? MDC.get(CORRELATION_ID) : randomUUID().toString();
        return KafkaMessage.builder()
            .sourceEvent(event)
            .correlationId(correlationId)
            .applicationName(EnvAccessor.getEnv(SPRING_APPLICATION_NAME))
            .build();
    }
}
