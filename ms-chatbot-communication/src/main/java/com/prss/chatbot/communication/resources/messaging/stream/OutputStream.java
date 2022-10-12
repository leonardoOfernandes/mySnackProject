package com.prss.chatbot.communication.resources.messaging.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OutputStream {

    String PRE_CONSUMER_APPROVED_CONSUMER_CARD_HUB_OUTPUT = "pre-consumer-approved-consumer-card-hub-output";
    String WAITING_LIST_COMMAND_EVENTS_OUTPUT = "waiting-list-command-events-output";
    String CARD_PRE_CONSUMER_REGISTRATION_COMPLETED_OUTPUT = "pre-consumer-registration-completed-output";
    String NOTIFICATION_OUTPUT = "notification-output";

    @Output(PRE_CONSUMER_APPROVED_CONSUMER_CARD_HUB_OUTPUT)
    MessageChannel preConsumerApprovedConsumerCardHubOutput();

    @Output(WAITING_LIST_COMMAND_EVENTS_OUTPUT)
    MessageChannel waitingListAddUserOutput();

    @Output(CARD_PRE_CONSUMER_REGISTRATION_COMPLETED_OUTPUT)
    MessageChannel preConsumerRegistrationCompletedOutput();

    @Output(NOTIFICATION_OUTPUT)
    MessageChannel notificationEvent();
}
