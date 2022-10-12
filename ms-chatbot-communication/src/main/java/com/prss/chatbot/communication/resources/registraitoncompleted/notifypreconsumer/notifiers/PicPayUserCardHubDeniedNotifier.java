package com.prss.chatbot.communication.resources.registraitoncompleted.notifypreconsumer.notifiers;

import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.EmailNotificationEvent;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.PushNotificationEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.picpay.cardpreconsumer.application.config.NotificationConfig.PICPAY_CLIENT_CREDIT_DENIED_TEMPLATE_EMAIL;
import static com.picpay.cardpreconsumer.resources.util.DeeplinkUtil.generateDeepLink;


/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Slf4j
@Component
public class PicPayUserCardHubDeniedNotifier extends PreConsumerNotifier {

    @Autowired
    public PicPayUserCardHubDeniedNotifier(PicUserCardHubApprovedWithActiveAccountNotifier next) {
        super(next);
    }

    @Override
    boolean isResponsible(final PreConsumerCompletedData data) {
        log.info("[send-notification]: PicPayUserCardHubDeniedNotification received data: {}", data);

        PreConsumer preConsumer = data.getPreConsumer();
        boolean hasCredit = data.getCreditAnalysis().hasCredit();

        boolean result = preConsumer.isPicPayUserCompleted() && !hasCredit;

        log.info("[send-notification]: PicPayUserCardHubDeniedNotification result: {}", result);

        return result;
    }

    @Override
    EmailNotificationEvent buildEmailEvent(final PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();

        EmailNotificationEvent event = createEmailEvent(preConsumer, PICPAY_CLIENT_CREDIT_DENIED_TEMPLATE_EMAIL);
        event.getParams().put("name", preConsumer.getFullName());
        event.getParams().put("deepLink", generateDeepLink(preConsumer.getId()));

        return event;
    }

    @Override
    PushNotificationEvent buildPushEvent(final PreConsumerCompletedData data) {
        return null;
    }
}
