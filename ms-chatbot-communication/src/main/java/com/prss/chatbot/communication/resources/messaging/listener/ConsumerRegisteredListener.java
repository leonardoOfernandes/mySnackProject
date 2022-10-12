package com.prss.chatbot.communication.resources.messaging.listener;

import com.newrelic.api.agent.Trace;
import com.picpay.cardpreconsumer.domain.exception.PreConsumerNotFoundException;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.domain.service.PreConsumerService;
import com.picpay.cardpreconsumer.resources.messaging.event.MessageBody;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.AccountSetupNewConsumerEventSource;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.CoreConsumerEventSource;
import com.picpay.cardpreconsumer.resources.messaging.filter.consumerevents.CoreConsumerEventsListenerFilter;
import com.picpay.cardpreconsumer.resources.messaging.filter.consumerevents.StreamListenerFilter;
import com.picpay.cardpreconsumer.resources.messaging.stream.InputStream;
import com.picpay.cardpreconsumer.resources.util.MaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerRegisteredListener {

    private static final String PRE_REGISTER_ORIGIN = "CARD";

    @Autowired
    private PreConsumerService preConsumerService;

    @Trace
    @StreamListener(InputStream.CORE_CONSUMER_EVENTS_INPUT)
    @StreamListenerFilter(CoreConsumerEventsListenerFilter.class)
    public void listenerConsumerRegistered(@Payload final MessageBody messageBody) {
        AccountSetupNewConsumerEventSource accountSetupNewConsumerEventSource = (AccountSetupNewConsumerEventSource) messageBody.getSource();
        try {
            if(!isOriginCard(accountSetupNewConsumerEventSource)) {
                return;
            }

            log.info("[listener-consumer-registered]: Received event, consumer {}", accountSetupNewConsumerEventSource);
            this.updatePreConsumerWithConsumerId(accountSetupNewConsumerEventSource);

        } catch (PreConsumerNotFoundException ex) {
            log.error("[listener-consumer-registered]: pre consumer not exists", ex);
        } catch (RuntimeException e) {
            log.error("[listener-consumer-registered]: core consumer events error", e);
            throw e;
        }
    }

    private boolean isOriginCard(AccountSetupNewConsumerEventSource accountSetupNewConsumerEventSource) {
        return PRE_REGISTER_ORIGIN.equalsIgnoreCase(accountSetupNewConsumerEventSource.getPreRegisterOrigin());
    }

    private void updatePreConsumerWithConsumerId(CoreConsumerEventSource coreConsumerEventSource) {
        log.info("[update-pre-consumer-id]: find pre-consumer, document number {}", MaskUtil.maskCpf(coreConsumerEventSource.getCpf()));
        PreConsumer preConsumerPersisted = preConsumerService.findByDocumentNumber(coreConsumerEventSource.getCpf());
        log.info("[update-pre-consumer-id]: pre-consumer was found, id {}", preConsumerPersisted.getId());
        preConsumerPersisted.setConsumerId(valueOf(coreConsumerEventSource.getId().trim()));
        preConsumerPersisted.getSourceChannels().forEach(sourceChannel -> sourceChannel.setConsumeId(valueOf(coreConsumerEventSource.getId().trim())));
        log.info("[update-pre-consumer-id]: pre-consumer-id updated, id {}", preConsumerPersisted.getConsumerId());
        preConsumerService.update(preConsumerPersisted);
    }
}

