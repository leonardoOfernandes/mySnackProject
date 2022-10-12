package com.prss.chatbot.communication.resources.registraitoncompleted.notifypreconsumer.handlers;

import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.notifypreconsumer.notifiers.NotPicPayUserCardHubApprovedNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */
@Component
public class PreConsumerNotifierHandler {

    @Autowired
    private NotPicPayUserCardHubApprovedNotifier notification;

    public void sendNotifications(PreConsumerCompletedData data) {
        notification.send(data);
    }
}
