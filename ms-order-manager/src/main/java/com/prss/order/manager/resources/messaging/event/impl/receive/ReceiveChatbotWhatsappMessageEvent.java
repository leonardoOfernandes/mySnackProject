package com.prss.order.manager.resources.messaging.event.impl.receive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prss.order.manager.resources.messaging.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ReceiveChatbotWhatsappMessageEvent implements Event {

    public static final String RECEIVE_WHATSAPP_MESSAGE = "RECEIVE_WHATSAPP_MESSAGE";

    @JsonProperty("is_first_message_of_day")
    private String isFirstMessageOfDay;

    @JsonProperty("client_phone")
    private String clientPhone;

    @JsonProperty("restaurant_phone")
    private String restaurantPhone;

    @JsonIgnore
    @Override
    public String getEventProducer() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getEventType() {
        return null;
    }
}
