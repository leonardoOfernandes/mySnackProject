package com.prss.chatbot.communication.resources.messaging.interceptors.input;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static java.util.Objects.nonNull;

/**
 * @author Carlos Romano
 * @version 1.0 28/11/2021
 */

@Slf4j
@Service
@GlobalChannelInterceptor(patterns = "*-input")
public class CorrelationIDChannelInterceptor implements ChannelInterceptor {

    private static final String CORRELATION_ID = "correlation_id";
    public static final String SPAM_ID = "spam_id";

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
        String correlationIdCreated =  UUID.randomUUID().toString();
        Object correlationIdReceived =  message.getHeaders().get("correlationId");
        if (nonNull(correlationIdReceived)) {
            correlationIdCreated = getCorrelationId(correlationIdReceived, correlationIdCreated);
        }

        MDC.put(CORRELATION_ID, correlationIdCreated);
        MDC.put(SPAM_ID, UUID.randomUUID().toString());

        return ChannelInterceptor.super.preSend(message, channel);
    }

    private String getCorrelationId(Object correlationIdReceived, String correlationIdCreated) {
        if (correlationIdReceived instanceof byte[]) {
            byte[] id = (byte[]) correlationIdReceived;
            return new String(id, StandardCharsets.UTF_8);
        } else if (Strings.isEmpty(correlationIdReceived.toString().trim())) {
            return correlationIdCreated;
        }else{
            return correlationIdReceived.toString().trim();
        }
    }
}
