package com.prss.order.manager.resources.messaging.exception;

public class EventTopicProducerException  extends RuntimeException {

    public EventTopicProducerException(String message, Throwable cause) {
        super(message, cause);
    }
}
