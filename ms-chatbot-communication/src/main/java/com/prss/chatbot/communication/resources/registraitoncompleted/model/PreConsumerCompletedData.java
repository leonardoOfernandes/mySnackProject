package com.prss.chatbot.communication.resources.registraitoncompleted.model;

import com.picpay.cardpreconsumer.domain.enums.RegistrationStatus;
import com.picpay.cardpreconsumer.domain.model.credit.CreditAnalysis;
import com.picpay.cardpreconsumer.domain.model.preconsumer.PreConsumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Carlos Romano
 * @version 1.0 07/01/2022
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreConsumerCompletedData {

    private PreConsumer preConsumer;

    private RegistrationStatus registrationStatus;

    private CreditAnalysis creditAnalysis;

    public PreConsumerCompletedData(PreConsumer preConsumer, CreditAnalysis creditAnalysis) {
        this.preConsumer = preConsumer;
        this.creditAnalysis = creditAnalysis;
    }
}
