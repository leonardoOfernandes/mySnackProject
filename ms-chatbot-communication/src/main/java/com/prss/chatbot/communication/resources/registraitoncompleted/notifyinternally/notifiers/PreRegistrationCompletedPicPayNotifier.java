package com.prss.chatbot.communication.resources.registraitoncompleted.notifyinternally.notifiers;

import com.picpay.cardpreconsumer.resources.messaging.broker.payload.KafkaMessage;
import com.picpay.cardpreconsumer.resources.messaging.producer.EventProducer;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.nonNull;

public abstract class PreRegistrationCompletedPicPayNotifier {

    @Autowired
    protected EventProducer producer;

    private PreRegistrationCompletedPicPayNotifier next;

    protected PreRegistrationCompletedPicPayNotifier(PreRegistrationCompletedPicPayNotifier next) {
        this.next = next;
    }

    public void send(PreConsumerCompletedData data) {
        if (isResponsible(data)) {
            KafkaMessage newRegisterEvent = buildKafkaMessage(data);
            producer.sendMessage(newRegisterEvent);
        } else if (nonNull(next)) {
            next.send(data);
        }
    }

    abstract boolean isResponsible(PreConsumerCompletedData data);


    abstract KafkaMessage buildKafkaMessage(PreConsumerCompletedData data);
}
