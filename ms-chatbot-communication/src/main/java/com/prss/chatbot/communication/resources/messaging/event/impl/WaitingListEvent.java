package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.product.ProductEnum;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class WaitingListEvent implements Event {
    private static final String CARD_PRE_REGISTER = "CARD_PRE_REGISTER";

    public static final String WAITING_LIST_ANALYSIS_WAS_APPROVED = "WAITING_LIST_ANALYSIS_WAS_APPROVED";
    public static final String WAITING_LIST_ANALYSIS_WAS_DENIED = "WAITING_LIST_ANALYSIS_WAS_DENIED";

    @JsonIgnore
    private String eventName;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("product_payload")
    private String productPayload;

    private String product;

    @JsonProperty("list_origin_flow")
    private List<String> listOriginFlow;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal limit;

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

    public boolean isApprovedAndHasPreConsumerOrigin() {
        return ProductEnum.CARD.name().equals(this.getProduct())
                && this.getListOriginFlow().contains(CARD_PRE_REGISTER)
                && this.getLimit().doubleValue() > BigDecimal.ZERO.doubleValue();
    }

    public CreditAnalysis toCreditAnalysis() {
        return CreditAnalysis
                .builder()
                .documentNumber(this.getDocumentNumber())
                .creditLimit(this.getLimit())
                .processed(false)
                .analysisStartedOn(LocalDateTime.now())
                .endAnalysisOn(LocalDateTime.now())
                .build();
    }
}
