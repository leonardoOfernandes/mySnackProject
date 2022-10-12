package com.prss.chatbot.communication.resources.messaging.listener;

import com.newrelic.api.agent.Trace;
import com.picpay.cardpreconsumer.domain.exception.VerificationPropertiesException;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.service.CreditAnalysisService;
import com.picpay.cardpreconsumer.domain.service.PreConsumerService;
import com.picpay.cardpreconsumer.resources.messaging.event.impl.CreditAnalysisResultEvent;
import com.picpay.cardpreconsumer.resources.messaging.stream.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreditAnalysisResultListener {

    @Autowired
    private CreditAnalysisService creditAnalysisService;

    @Autowired
    private PreConsumerService preConsumerService;

    @Trace
    @StreamListener(InputStream.CARD_HUB_CREDIT_ANALYSIS_LIMIT_ANALYSIS_INPUT)
    public void listenerHubCreditAnalysisResult(@Payload final CreditAnalysisResultEvent event) {
        log.info("[listener-hub-credit-analysis-result] received event: {}", event);

        process(event);
    }

    private void process(CreditAnalysisResultEvent event) {
        try {
            CreditAnalysis creditAnalysis = creditAnalysisService.createOrUpdate(toCreditAnalysis(event));
            log.info("[listener-credit-analysis-result] credit analysis created ,id:{}", creditAnalysis.getId());
            preConsumerService.preConsumerRegistrationDone(creditAnalysis);
        } catch (VerificationPropertiesException ex) {
            log.error("[listener-credit-analysis-result] error during verification of pre consumer properties to send the analysis result", ex);
            throw ex;
        } catch (RuntimeException ex) {
            log.error("[listener-credit-analysis-result] error during event processing.", ex);
            throw ex;
        }
    }

    private CreditAnalysis toCreditAnalysis(CreditAnalysisResultEvent eventSource) {
        return CreditAnalysis
                .builder()
                .documentNumber(eventSource.getDocumentNumber())
                .creditLimit(eventSource.getLimit())
                .source(eventSource.getSource())
                .processed(false)
                .analysisStartedOn(eventSource.getAnalysisStartedOn())
                .endAnalysisOn(eventSource.getAnalysisEndedOn())
                .build();
    }
}