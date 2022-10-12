package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import com.picpay.cardpreconsumer.resources.messaging.stream.OutputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewPreConsumerEvent implements Event {

    public static final String REQUEST_CONSUMER_CREATION = "REQUEST_PRE_CONSUMER_REGISTRATION";

    @JsonProperty("pre_consumer_id")
    private String preConsumerId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("document_number")
    private String documentNumber;

    private String email;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("area_code")
    private String areaCode;

    private String phone;

    @Override
    @JsonIgnore
    public String getEventProducer() {
        return OutputStream.CARD_PRE_CONSUMER_REGISTRATION_COMPLETED_OUTPUT;
    }

    @Override
    @JsonIgnore
    public String getEventType() {
        return REQUEST_CONSUMER_CREATION;
    }

    public static NewPreConsumerEvent from(PreConsumer preConsumer) {
        return NewPreConsumerEvent
            .builder()
            .preConsumerId(preConsumer.getId())
            .fullName(preConsumer.getFullName())
            .documentNumber(preConsumer.getDocumentNumber())
            .birthDate(preConsumer.getBirthDate())
            .email(preConsumer.getEmail())
            .areaCode(preConsumer.getAreaCode())
            .phone(preConsumer.getPhone())
            .build();
    }
}
