package com.prss.order.manager.resources.messaging.event.impl.send;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prss.order.manager.resources.messaging.event.Event;
import com.prss.order.manager.resources.messaging.stream.OutputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendChatbotWhatsappMessageEvent implements Event {

    public static final String SEND_WHATSAPP_MESSAGE = "SEND_WHATSAPP_MESSAGE";

    @JsonProperty("restaurant_phone")
    private String restaurantPhone;

    @JsonProperty("client_phone")
    private String clientPhone;

    private String message;

    @JsonProperty("message_type")
    private String messageType;

    @Override
    @JsonIgnore
    public String getEventProducer() {
        return OutputStream.SEND_CHATBOT_WHATSAPP_MESSAGE_OUTPUT;
    }

    @Override
    @JsonIgnore
    public String getEventType() {
        return SEND_WHATSAPP_MESSAGE;
    }
}
