package com.prss.chatbot.communication.resources.messaging.filter.consumerevents;

import com.picpay.cardpreconsumer.resources.messaging.event.MessageBody;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.AccountSetupNewConsumerEventSource;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.CoreConsumerEventSource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Component
@RequiredArgsConstructor
public class CoreConsumerEventsListenerFilter implements ListenerFilter<MessageBody> {

    private static final String VALIDATE_VERSION = "2";

    @Override
    public boolean validate(MessageBody body) {
        if (isNull(body)) {
            return false;
        }

        final String event = body.getEvent();
        final CoreConsumerEventSource source = (CoreConsumerEventSource) body.getSource();

        return nonNull(event) && nonNull(source) &&
            StringUtils.equals(AccountSetupNewConsumerEventSource.ACCOUNT_SETUP_NEW_CONSUMER, event) &&
            StringUtils.equals(VALIDATE_VERSION, source.getAccountSetupValidateVersion());
    }
}