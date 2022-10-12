package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.domain.model.product.ProductEnum;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import com.picpay.cardpreconsumer.resources.messaging.stream.OutputStream;
import com.picpay.cardpreconsumer.resources.security.wrapper.documentnumber.DocumentNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaitingListCommandEvent implements Event {

    private static final ObjectMapper MAPPER = new ObjectMapper()
                .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());

    public static final String WAITING_LIST_EVENT = "WAITING_LIST_ADD_USER";
    private static final String CARD_PRE_REGISTER = "CARD_PRE_REGISTER";

    @JsonProperty("document_number")
    private DocumentNumber documentNumber;

    @JsonIgnore
    private String eventName;

    @JsonProperty("product_payload")
    private String productPayload;

    private String product;

    @JsonProperty("origin_flow")
    private String originFlow;

    @Override
    @JsonIgnore
    public String getEventProducer() {
        return OutputStream.WAITING_LIST_COMMAND_EVENTS_OUTPUT;
    }

    @Override
    @JsonIgnore
    public String getEventType() {
        return WAITING_LIST_EVENT;
    }

    public static WaitingListCommandEvent from(PreConsumer preConsumer) {
        PayloadCard payload = new PayloadCard(preConsumer.getFullName(), preConsumer.getBirthDate(), preConsumer.getEmail());

        return WaitingListCommandEvent
            .builder()
            .documentNumber(new DocumentNumber(preConsumer.getDocumentNumber()))
            .originFlow(CARD_PRE_REGISTER)
            .product(ProductEnum.CARD.name())
            .productPayload(toJson(payload))
            .build();
    }

    private static String toJson(PayloadCard payload) {
        try {
            return MAPPER.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("error in payload card convertion to json", e);
            return null;
        }
    }

    @Data
    @AllArgsConstructor
    public static class PayloadCard {

        @JsonProperty("full_name")
        private String fullName;

        @JsonProperty("birth_date")
        private LocalDate birthDate;

        private String email;
    }
}
