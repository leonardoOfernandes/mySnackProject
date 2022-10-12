package com.prss.order.manager.resources.messaging.event;

public interface Event {

    String getEventProducer();

    String getEventType();
}
