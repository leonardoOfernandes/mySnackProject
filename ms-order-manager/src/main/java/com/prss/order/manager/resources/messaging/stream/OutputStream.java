package com.prss.order.manager.resources.messaging.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OutputStream {
    String SEND_CHATBOT_WHATSAPP_MESSAGE_OUTPUT = "send-chatbot-whatsapp-message-output";

    @Output(SEND_CHATBOT_WHATSAPP_MESSAGE_OUTPUT)
    MessageChannel sendChatbotWhatsappMessageOutput();
}
