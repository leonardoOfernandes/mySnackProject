package com.prss.chatbot.communication.resources.messaging.event.impl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AccountSetupNewConsumerEventSource extends CoreConsumerEventSource {

    public static final String ACCOUNT_SETUP_NEW_CONSUMER = "ACCOUNT_SETUP_NEW_CONSUMER";

    @JsonProperty("pre_register_origin")
    private String preRegisterOrigin;

    @JsonIgnore
    @Override
    public String getEventType() {
        return ACCOUNT_SETUP_NEW_CONSUMER;
    }

}