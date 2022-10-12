package com.prss.chatbot.communication.resources.messaging.filter.consumerevents;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StreamListenerFilter {

    Class<?  extends ListenerFilter> value();

}
