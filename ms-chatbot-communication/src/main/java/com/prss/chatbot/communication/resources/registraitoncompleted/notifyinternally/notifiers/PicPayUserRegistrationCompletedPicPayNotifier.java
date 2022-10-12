package com.prss.chatbot.communication.resources.registraitoncompleted.notifyinternally.notifiers;

import com.picpay.cardpreconsumer.domain.enums.RegistrationStatus;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.broker.payload.KafkaMessage;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.ApprovedConsumerCardHubEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PicPayUserRegistrationCompletedPicPayNotifier extends PreRegistrationCompletedPicPayNotifier {

    public PicPayUserRegistrationCompletedPicPayNotifier() {
        super(null);
    }

    @Override
    boolean isResponsible(PreConsumerCompletedData data) {
        log.info("[send-notification]: PicPayUserRegistrationCompletedPicPayNotifier received data: {}", data);

        PreConsumer preConsumer = data.getPreConsumer();
        RegistrationStatus registrationStatus = data.getRegistrationStatus();
        boolean hasCredit = data.getCreditAnalysis().hasCredit();

        boolean result = preConsumer.isPicPayUserCompleted() &&
                RegistrationStatus.isApt(registrationStatus) && hasCredit;

        log.info("[send-notification]: PicPayUserRegistrationCompletedPicPayNotifier result: {}", result);

        return result;
    }

    @Override
    KafkaMessage buildKafkaMessage(PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();
        CreditAnalysis creditAnalysis = data.getCreditAnalysis();
        ApprovedConsumerCardHubEvent event= ApprovedConsumerCardHubEvent
                .builder()
                .preConsumerId(preConsumer.getId())
                .consumerId(preConsumer.getConsumerId())
                .documentNumber(preConsumer.getDocumentNumber())
                .creditLimit(creditAnalysis.getCreditLimit())
                .build();

        return KafkaMessage.build(event);
    }
}
