package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import com.picpay.cardpreconsumer.resources.messaging.stream.OutputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailNotificationEvent implements Event {

    public static final String NOTIFICATION_EVENT = "PRE_CONSUMER_NOTIFICATION_WAS_CREATED";

    private Channel channel;
    private String template;
    private String receiver;
    @JsonProperty("receiver_name")
    private String receiverName;
    @JsonProperty("receiver_email")
    private String receiverEmail;
    @JsonProperty("external_id")
    private UUID externalId;
    @JsonProperty("created_at")
    private Date createdAt;
    private Map<String, String> params;
    
    @JsonIgnore
    @Override
    public String getEventProducer() {
        return OutputStream.NOTIFICATION_OUTPUT;
    }

    @JsonIgnore
    @Override
    public String getEventType() {
        return NOTIFICATION_EVENT;
    }

    public enum Channel {
        EMAIL,
    }

}
