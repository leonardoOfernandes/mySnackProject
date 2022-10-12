package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreConsumerEventSource implements Event {

    public static final String CONSUMER_ACTIVATE = "CONSUMER_ACTIVATE";
    public static final String CONSUMER_WAS_UPDATED = "CONSUMER_WAS_UPDATED";
    public static final String CONSUMER_DEACTIVATE = "CONSUMER_DEACTIVATE";
    public static final String CONSUMER_WAS_CREATED = "CONSUMER_WAS_CREATED";
    public static final String CONSUMER_WAS_DELETED = "CONSUMER_WAS_DELETED";

    protected String id;
    protected String cpf;
    protected String ip;

    @JsonProperty("account_setup_validate_version")
    protected String accountSetupValidateVersion;

    @JsonIgnore
    @Override
    public String getEventProducer() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getEventType() {
        return "";
    }
}
