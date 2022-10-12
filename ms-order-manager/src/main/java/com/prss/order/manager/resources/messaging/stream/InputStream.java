package com.prss.order.manager.resources.messaging.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface InputStream {
    String RECEIVE_CHATBOT_WHATSAPP_MESSAGE_INPUT = "receive-chatbot-whatsapp-message-input";

    @Input(RECEIVE_CHATBOT_WHATSAPP_MESSAGE_INPUT)
    SubscribableChannel receiveChatbotWhatsappMessageInput();

}
