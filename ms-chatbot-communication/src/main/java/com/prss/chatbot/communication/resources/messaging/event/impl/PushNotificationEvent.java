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
public class PushNotificationEvent implements Event {
    public static final String NOTIFICATION_EVENT = "PRE_CONSUMER_NOTIFICATION_WAS_CREATED";
    public static final String CARD_PRE_CONSUMER_REGISTRATION_DEEPLINK = "services.deeplink-registration.url";

    private Channel channel;
    private Action action;
    private String template;
    private Sender sender;
    private String receiver;
    @JsonProperty("receiver_type")
    private ReceiverType receiverType;
    private Map<String, String> params;
    @JsonProperty("external_id")
    private UUID externalId;
    private Boolean push;
    @JsonProperty("created_at")
    private Date createdAt;

    
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
        APP
    }
    public enum Action {
        CREATE
    }

    public enum ReceiverType {
        CONSUMER
    }

    public enum Sender {
        SENDER
    }

}
