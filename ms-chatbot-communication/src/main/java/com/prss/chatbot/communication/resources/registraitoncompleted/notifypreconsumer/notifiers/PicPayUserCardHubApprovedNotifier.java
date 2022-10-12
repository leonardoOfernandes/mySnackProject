package com.prss.chatbot.communication.resources.registraitoncompleted.notifypreconsumer.notifiers;

import com.picpay.cardpreconsumer.application.config.NotificationConfig;
import com.picpay.cardpreconsumer.domain.enums.RegistrationStatus;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.EmailNotificationEvent;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.PushNotificationEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import com.picpay.cardpreconsumer.resources.util.CurrencyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.picpay.cardpreconsumer.application.config.NotificationConfig.PICPAY_CLIENT_CREDIT_APPROVED_TEMPLATE_EMAIL;
import static com.picpay.cardpreconsumer.domain.enums.RegistrationStatus.isApt;
import static com.picpay.cardpreconsumer.resources.util.DeeplinkUtil.generateDeepLink;
import static com.picpay.cardpreconsumer.resources.util.DeeplinkUtil.generateQrCodeLinkPreConsumerApproved;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Slf4j
@Component
public class PicPayUserCardHubApprovedNotifier extends PreConsumerNotifier {

    @Autowired
    public PicPayUserCardHubApprovedNotifier(PicPayUserCardHubDeniedNotifier next) {
        super(next);
    }

    @Override
    boolean isResponsible(final PreConsumerCompletedData data) {
        log.info("[send-notification]: PicPayUserCardHubApprovedNotification received data: {}", data);

        PreConsumer preConsumer = data.getPreConsumer();
        RegistrationStatus registrationStatus = data.getRegistrationStatus();
        boolean hasCredit = data.getCreditAnalysis().hasCredit();

        boolean result = preConsumer.isPicPayUserCompleted() &&
                         isApt(registrationStatus) &&
                         hasCredit;

        log.info("[send-notification]: PicPayUserCardHubApprovedNotification result: {}", result);

        return result;
    }

    @Override
    EmailNotificationEvent buildEmailEvent(final PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();
        CreditAnalysis creditAnalysis = data.getCreditAnalysis();

        EmailNotificationEvent emailEvent = createEmailEvent(data.getPreConsumer(),
            PICPAY_CLIENT_CREDIT_APPROVED_TEMPLATE_EMAIL);

        emailEvent.getParams().put("name", preConsumer.getFullName());
        emailEvent.getParams().put("value", CurrencyUtils.realFormat(creditAnalysis.getCreditLimit()));
        emailEvent.getParams().put("QRCodeLink", generateQrCodeLinkPreConsumerApproved(preConsumer.getId()));
        emailEvent.getParams().put("deepLink", generateDeepLink(preConsumer.getId()));

        return emailEvent;
    }

    @Override
    PushNotificationEvent buildPushEvent(final PreConsumerCompletedData data) {
        PreConsumer preConsumer = data.getPreConsumer();

        return createPushEvent(preConsumer, NotificationConfig.PICPAY_CLIENT_CREDIT_APPROVED_TEMPLATE_PUSH);
    }
}
