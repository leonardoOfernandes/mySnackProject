package com.prss.chatbot.communication.resources.messaging.listener;

import com.newrelic.api.agent.Trace;
import com.picpay.cardpreconsumer.domain.exception.VerificationPropertiesException;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.service.CreditAnalysisService;
import com.picpay.cardpreconsumer.domain.service.PreConsumerService;
import com.picpay.cardpreconsumer.resources.messaging.event.MessageBody;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.WaitingListEvent;
import com.picpay.cardpreconsumer.resources.messaging.stream.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WaitingListResultListener {
    private static final String CARD_PRE_REGISTER = "CARD_PRE_REGISTER";

    @Autowired
    private CreditAnalysisService creditAnalysisService;

    @Autowired
    private PreConsumerService preConsumerService;

    @Trace
    @StreamListener(InputStream.WAITING_LIST_EVENTS_INPUT)
    public void waitingListResult(@Payload final MessageBody messageBody) {
        WaitingListEvent event = (WaitingListEvent) messageBody.getSource();

        try {
            if(event.isApprovedAndHasPreConsumerOrigin()) {
                CreditAnalysis creditAnalysis = creditAnalysisService.createOrUpdate(event.toCreditAnalysis());
                log.info("[waiting-list-analysis-result] credit analysis created ,id:{}", creditAnalysis.getId());
                preConsumerService.preConsumerRegistrationDone(creditAnalysis);
            }
        } catch (VerificationPropertiesException ex) {
            log.error("[waiting-list-analysis-result] error during verification of pre consumer properties to send the analysis result", ex);
            throw ex;
        } catch (RuntimeException ex) {
            log.error("[waiting-list-analysis-result] error during event processing.", ex);
            throw ex;
        }
    }
}