package com.prss.chatbot.communication.resources.messaging.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface InputStream {

    String CARD_HUB_CREDIT_ANALYSIS_LIMIT_ANALYSIS_INPUT = "card-hub-credit-analysis-limit-analysis-input";
    String CORE_CONSUMER_EVENTS_INPUT = "core-consumer-events-input";
    String WAITING_LIST_EVENTS_INPUT = "waiting-list-events-input";

    @Input(CARD_HUB_CREDIT_ANALYSIS_LIMIT_ANALYSIS_INPUT)
    SubscribableChannel cardHubCreditAnalysisLimitAnalysisInput();

    @Input(CORE_CONSUMER_EVENTS_INPUT)
    SubscribableChannel coreConsumerEventsInput();

    @Input(WAITING_LIST_EVENTS_INPUT)
    SubscribableChannel waitingListEventsInput();

}
