package com.prss.chatbot.communication.resources.messaging.event.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.picpay.cardpreconsumer.resources.messaging.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditAnalysisResultEvent implements Event {

    public static final String LIMIT_ANALYSIS_RESULT = "LIMIT_ANALYSIS_RESULT";

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal limit;

    private String source;

    @JsonProperty("analysis_started_on")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime analysisStartedOn;

    @JsonProperty("analysis_ended_on")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime analysisEndedOn;

    @Override
    @JsonIgnore
    public String getEventProducer() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getEventType() {
        return LIMIT_ANALYSIS_RESULT;
    }
}
