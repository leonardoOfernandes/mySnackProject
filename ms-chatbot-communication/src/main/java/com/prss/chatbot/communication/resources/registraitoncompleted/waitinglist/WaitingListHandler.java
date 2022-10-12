package com.prss.chatbot.communication.resources.registraitoncompleted.waitinglist;

import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.broker.payload.KafkaMessage;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.WaitingListCommandEvent;
import com.picpay.cardpreconsumer.resources.messaging.producer.EventProducer;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

/**
 * @author Carlos Romano
 * @version 1.0 14/01/2022
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class WaitingListHandler {

    private final EventProducer producer;

    public void handleRegistrationComplete(PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();
        CreditAnalysis creditAnalysis = data.getCreditAnalysis();

        log.info("[WaitingListHandler]: data received to analysis if will send to waiting list: {}", data);
        if (preConsumer.isAptToWaitingList() && nonNull(creditAnalysis) &&
            !creditAnalysis.hasCredit()) {

            WaitingListCommandEvent event = WaitingListCommandEvent.from(preConsumer);

            producer.sendMessageBody(KafkaMessage.build(event));
        }
    }
}
