package com.prss.chatbot.communication.resources.registraitoncompleted;

import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import com.picpay.cardpreconsumer.domain.model.registrationresponse.RegistrationResponse;
import com.picpay.cardpreconsumer.domain.service.CreditAnalysisService;
import com.picpay.cardpreconsumer.resources.client.cardregistration.CardRegistrationClient;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.model.PreConsumerCompletedData;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.notifyinternally.handlers.NotifyPicPayPreConsumerCompletedHandler;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.notifypreconsumer.handlers.PreConsumerNotifierHandler;
import com.picpay.cardpreconsumer.resources.registraitoncompleted.waitinglist.WaitingListHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.picpay.cardpreconsumer.domain.enums.RegistrationStatus.fromValue;
import static java.util.Objects.nonNull;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Slf4j
@Component
public class PreConsumerRegistrationCompletedHandler {

    @Autowired
    private PreConsumerNotifierHandler preConsumerNotifierHandler;

    @Autowired
    private NotifyPicPayPreConsumerCompletedHandler notifyPicPayPreConsumerCompletedHandler;

    @Autowired
    private CardRegistrationClient cardRegistrationClient;

    @Autowired
    private CreditAnalysisService creditAnalysisService;

    @Autowired
    private WaitingListHandler waitingListHandler;

    public void handle(PreConsumerCompletedData data) {
        log.info("[pre-consumer-completed]: RegistrationCompletedHandler received data: {}", data);
        PreConsumer preConsumer = data.getPreConsumer();
        CreditAnalysis creditAnalysis = data.getCreditAnalysis();

        final boolean picPayUserCompleted = preConsumer.isPicPayUserCompleted();
        if (picPayUserCompleted && nonNull(creditAnalysis)) {
            RegistrationResponse registrationResponse = cardRegistrationClient.findRegistrationUserByConsumerId(preConsumer.getConsumerId().toString());
            log.info("[pre-consumer-completed]: registration data response: {}", registrationResponse);
            if (nonNull(registrationResponse)) {
                data.setRegistrationStatus(fromValue(registrationResponse.getRegistrationStatus()));
            }
        }

        if (shouldNotify(preConsumer, creditAnalysis, picPayUserCompleted)) {
            log.info("[pre-consumer-completed]: RegistrationCompletedHandler send notifications to: {}", data);
            preConsumerNotifierHandler.sendNotifications(data);
            notifyPicPayPreConsumerCompletedHandler.sendNewRegister(data);
            creditAnalysisService.markAsProcessed(creditAnalysis);
        }

        waitingListHandler.handleRegistrationComplete(data);
    }

    private boolean shouldNotify(final PreConsumer preConsumer, final CreditAnalysis creditAnalysis, final boolean picPayUserCompleted) {
        return (picPayUserCompleted ||
                preConsumer.registrationCompleted()) &&
                nonNull(creditAnalysis) &&
                !creditAnalysis.isProcessed();
    }
}