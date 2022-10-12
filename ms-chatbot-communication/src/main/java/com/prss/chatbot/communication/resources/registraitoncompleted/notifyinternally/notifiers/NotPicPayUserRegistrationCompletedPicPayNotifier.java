package com.prss.chatbot.communication.resources.registraitoncompleted.notifyinternally.notifiers;

import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.broker.payload.KafkaMessage;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.NewPreConsumerEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotPicPayUserRegistrationCompletedPicPayNotifier extends PreRegistrationCompletedPicPayNotifier {

    public NotPicPayUserRegistrationCompletedPicPayNotifier(PicPayUserRegistrationCompletedPicPayNotifier next) {
        super(next);
    }

    @Override
    boolean isResponsible(PreConsumerCompletedData data) {
        log.info("[send-notification]: NotPicPayUserRegistrationCompletedPicPayNotifier received data: {}", data);

        PreConsumer preConsumer = data.getPreConsumer();
        boolean hasCredit = data.getCreditAnalysis().hasCredit();

        boolean result = preConsumer.registrationCompleted() &&
                hasCredit &&
                !preConsumer.isConsumer();

        log.info("[send-notification]: NotPicPayUserRegistrationCompletedPicPayNotifier result: {}", result);

        return result;
    }

    @Override
    KafkaMessage buildKafkaMessage(PreConsumerCompletedData data) {

        PreConsumer preConsumer = data.getPreConsumer();
        NewPreConsumerEvent event= NewPreConsumerEvent
                .builder()
                .preConsumerId(preConsumer.getId())
                .fullName(preConsumer.getFullName())
                .documentNumber(preConsumer.getDocumentNumber())
                .birthDate(preConsumer.getBirthDate())
                .email(preConsumer.getEmail())
                .areaCode(preConsumer.getAreaCode())
                .phone(preConsumer.getPhone())
                .build();

        return KafkaMessage.build(event);
    }
}
