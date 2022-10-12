package com.prss.chatbot.communication.resources.messaging.broker.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class MessageHeaderPreConsumer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "application/json";
    private static final String APPLICATION_NAME = "ms-card-pre-consumer";
    private static final String REGISTER_ORIGIN = "CARD";

    private String contentType;
    private String applicationName;
    private String eventId;
    private String eventName;
    private String correlationId;
    private String spamId;
    @JsonProperty("pre_register_origin")
    private String preRegisterOrigin;

    public static MessageHeaderPreConsumer of(String eventName, KafkaMessage kafkaMessage) {
        return MessageHeaderPreConsumer.builder()
                .contentType(CONTENT_TYPE)
                .applicationName(APPLICATION_NAME)
                .eventId(UUID.randomUUID().toString())
                .eventName(eventName)
                .correlationId(StringUtils.isNotBlank(kafkaMessage.getCorrelationId()) ? kafkaMessage.getCorrelationId() : UUID.randomUUID().toString())
                .preRegisterOrigin(REGISTER_ORIGIN)
                .build();
    }


    public static Map<String, String> buildMapToConsumer(String eventName, KafkaMessage kafkaMessage) {

        MessageHeaderPreConsumer headersPayload = MessageHeaderPreConsumer.of(eventName, kafkaMessage);
        return objectMapper.convertValue(headersPayload, new TypeReference<>() {});
    }

}
