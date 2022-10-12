package com.prss.order.manager.resources.messaging.listener;

import com.prss.order.manager.resources.messaging.event.MessageBody;
import com.prss.order.manager.resources.messaging.event.impl.receive.ReceiveChatbotWhatsappMessageEvent;
import com.prss.order.manager.resources.messaging.stream.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveChatbotWhatsappMessageListener {
    private static final String CARD_PRE_REGISTER = "CARD_PRE_REGISTER";

    @StreamListener(InputStream.RECEIVE_CHATBOT_WHATSAPP_MESSAGE_INPUT)
    public void receiveChatbotWhatsappMessage(@Payload final MessageBody messageBody) {
        ReceiveChatbotWhatsappMessageEvent event = (ReceiveChatbotWhatsappMessageEvent) messageBody.getSource();

        try {

        } catch (RuntimeException ex) {
            log.error("[waiting-list-analysis-result] error during event processing.", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("[waiting-list-analysis-result] error during verification of pre consumer properties to send the analysis result", ex);
            throw ex;
        }
    }
}