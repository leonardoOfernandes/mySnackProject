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

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovedConsumerCardHubEvent implements Event {

    @JsonProperty("pre_consumer_id")
    private String preConsumerId;

    @JsonProperty("consumer_id")
    private Integer consumerId;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("credit_limit")
    private BigDecimal creditLimit;

    @Override
    @JsonIgnore
    public String getEventProducer() {
        return OutputStream.PRE_CONSUMER_APPROVED_CONSUMER_CARD_HUB_OUTPUT;
    }

    @Override
    @JsonIgnore
    public String getEventType() {
        return null;
    }
}