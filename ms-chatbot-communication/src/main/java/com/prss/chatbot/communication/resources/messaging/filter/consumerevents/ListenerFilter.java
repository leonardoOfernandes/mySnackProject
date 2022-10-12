package com.prss.chatbot.communication.resources.messaging.filter.consumerevents;

public interface ListenerFilter<T> {

    boolean validate(T t);

}