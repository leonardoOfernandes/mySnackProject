package com.prss.chatbot.communication.resources.registraitoncompleted.notifypreconsumer.notifiers;

import com.picpay.cardpreconsumer.domain.enums.RegistrationStatus;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.EmailNotificationEvent;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.PushNotificationEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.picpay.cardpreconsumer.application.config.NotificationConfig.PICPAY_CLIENT_CARD_ACTIVATED_TEMPLATE_EMAIL;
import static com.picpay.cardpreconsumer.resources.util.DeeplinkUtil.generateDeepLink;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Slf4j
@Component
public class PicUserCardHubApprovedWithActiveAccountNotifier extends PreConsumerNotifier {

    public PicUserCardHubApprovedWithActiveAccountNotifier(PicPayUserCardHubApprovedWithActivationInProgressNotifier next) {
        super(next);
    }

    @Override
    boolean isResponsible(final PreConsumerCompletedData data) {
        log.info("[send-notification]: PicUserCardHubApprovedWithActiveAccountNotifier received data: {}", data);

        PreConsumer preConsumer = data.getPreConsumer();
        boolean hasCredit = data.getCreditAnalysis().hasCredit();
        RegistrationStatus status = data.getRegistrationStatus();
        boolean activeCardAccount = RegistrationStatus.activeCardAccount(status);

        boolean result = preConsumer.isPicPayUserCompleted() && hasCredit && activeCardAccount;

        log.info("[send-notification]: PicUserCardHubApprovedWithActiveAccountNotifier result: {}", result);

        return result;
    }

    @Override
    EmailNotificationEvent buildEmailEvent(final PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();

        EmailNotificationEvent emailEvent = createEmailEvent(data.getPreConsumer(),
            PICPAY_CLIENT_CARD_ACTIVATED_TEMPLATE_EMAIL);

        emailEvent.getParams().put("name", preConsumer.getFullName());
        emailEvent.getParams().put("deepLink", generateDeepLink(preConsumer.getId()));


        return emailEvent;
    }

    @Override
    PushNotificationEvent buildPushEvent(final PreConsumerCompletedData data) {
        return null;
    }
}
