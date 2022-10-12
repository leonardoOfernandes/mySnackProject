package com.prss.chatbot.communication.resources.registraitoncompleted.notifypreconsumer.notifiers;

import com.picpay.cardpreconsumer.domain.exception.PreConsumerNotifierNotFoundException;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.domain.service.NotificationService;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.EmailNotificationEvent;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.PushNotificationEvent;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */
@Slf4j
public abstract class PreConsumerNotifier {

    private static final String DEEP_LINK_REGISTRATION_URL = "picpay://picpay/credit/registration";

    @Autowired
    protected NotificationService notificationService;

    private final PreConsumerNotifier next;

    protected PreConsumerNotifier(PreConsumerNotifier next) {
        this.next = next;
    }

    public void send(PreConsumerCompletedData data) {
        if (isResponsible(data)) {
            EmailNotificationEvent emailEvent = buildEmailEvent(data);
            PushNotificationEvent pushEvent = buildPushEvent(data);

            notificationService.sendEmailMessage(emailEvent);
            notificationService.sendPushMessage(pushEvent);
        } else if (nonNull(next)) {
            next.send(data);
        } else {
            log.error("[pre-consumer-notifier] - notifier not hound to pre consumer data: {}", data);
            throw new PreConsumerNotifierNotFoundException();
        }
    }

    abstract boolean isResponsible(PreConsumerCompletedData data);

    abstract EmailNotificationEvent buildEmailEvent(PreConsumerCompletedData data);

    abstract PushNotificationEvent buildPushEvent(PreConsumerCompletedData data);

    protected EmailNotificationEvent createEmailEvent(final PreConsumer preConsumer, final String template) {

        UUID externalId = UUID.randomUUID();
        Map<String, String> params = new HashMap<>();

        return EmailNotificationEvent.builder()
            .template(template)
            .createdAt(Date.from(Instant.now()))
            .channel(EmailNotificationEvent.Channel.EMAIL)
            .params(params)
            .receiverEmail(preConsumer.getEmail())
            .receiverName(preConsumer.getFullName())
            .externalId(externalId)
            .build();
    }

    protected PushNotificationEvent createPushEvent(final PreConsumer preConsumer, final String template) {

        UUID externalId = UUID.randomUUID();
        Map<String, String> params = new HashMap<>();

        params.put("deeplink", DEEP_LINK_REGISTRATION_URL);

        return PushNotificationEvent.builder()
                .channel(PushNotificationEvent.Channel.APP)
                .action(PushNotificationEvent.Action.CREATE)
                .template(template)
                .sender(PushNotificationEvent.Sender.SENDER)
                .receiver(preConsumer.getConsumerId().toString())
                .receiverType(PushNotificationEvent.ReceiverType.CONSUMER)
                .params(params)
                .externalId(externalId)
                .push(Boolean.TRUE)
                .createdAt(Date.from(Instant.now()))
                .build();
    }
}
