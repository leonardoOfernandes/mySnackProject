package com.prss.order.manager.resources.messaging.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageBody {

    private String event;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "event")
    @JsonSubTypes(value = @JsonSubTypes.Type(value = ReceiveChatbotWhatsappMessageEvent.class, name = ReceiveChatbotWhatsappMessageEvent.RECEIVE_WHATSAPP_MESSAGE))
    private Event source;
}
