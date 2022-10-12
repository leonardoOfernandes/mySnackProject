package com.prss.chatbot.communication.resources.messaging.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.*;
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
    @JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CreditAnalysisResultEvent.class, name = CreditAnalysisResultEvent.LIMIT_ANALYSIS_RESULT),
        @JsonSubTypes.Type(value = NewPreConsumerEvent.class, name = NewPreConsumerEvent.REQUEST_CONSUMER_CREATION),
        @JsonSubTypes.Type(value = AccountSetupNewConsumerEventSource.class, name = AccountSetupNewConsumerEventSource.ACCOUNT_SETUP_NEW_CONSUMER),
        @JsonSubTypes.Type(value = CoreConsumerEventSource.class, name = CoreConsumerEventSource.CONSUMER_WAS_UPDATED),
        @JsonSubTypes.Type(value = CoreConsumerEventSource.class, name = CoreConsumerEventSource.CONSUMER_ACTIVATE),
        @JsonSubTypes.Type(value = CoreConsumerEventSource.class, name = CoreConsumerEventSource.CONSUMER_DEACTIVATE),
        @JsonSubTypes.Type(value = CoreConsumerEventSource.class, name = CoreConsumerEventSource.CONSUMER_WAS_CREATED),
        @JsonSubTypes.Type(value = CoreConsumerEventSource.class, name = CoreConsumerEventSource.CONSUMER_WAS_DELETED),
        @JsonSubTypes.Type(value = WaitingListEvent.class, name = WaitingListEvent.WAITING_LIST_ANALYSIS_WAS_APPROVED),
        @JsonSubTypes.Type(value = WaitingListEvent.class, name = WaitingListEvent.WAITING_LIST_ANALYSIS_WAS_DENIED),
    })
    private Event source;
}
