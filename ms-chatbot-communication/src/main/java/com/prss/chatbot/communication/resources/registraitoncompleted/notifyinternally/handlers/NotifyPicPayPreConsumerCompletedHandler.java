package com.prss.chatbot.communication.resources.registraitoncompleted.notifyinternally.handlers;

import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.notifyinternally.notifiers.NotPicPayUserRegistrationCompletedPicPayNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyPicPayPreConsumerCompletedHandler {

    @Autowired
    private NotPicPayUserRegistrationCompletedPicPayNotifier newRegister;

    public void sendNewRegister(PreConsumerCompletedData data) {
        newRegister.send(data);
    }
}
