package com.prss.chatbot.communication.resources.messaging.filter.consumerevents;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@Slf4j
@Value
@Aspect
@Component
public class StreamListenerAspect {

    @Autowired
    ApplicationContext context;

    @Pointcut(value = "@annotation(streamListenerFilter)")
    public void pointcut(StreamListenerFilter streamListenerFilter) {
        // Empty method
    }

    @Around("pointcut(streamListenerFilter)")
    public void interceptListener(ProceedingJoinPoint joinPoint, StreamListenerFilter streamListenerFilter) throws Throwable {
        ListenerFilter filter = context.getBean(streamListenerFilter.value());

        val type = (Class) ((ParameterizedType) streamListenerFilter.value().getGenericInterfaces()[0]).getActualTypeArguments()[0];

        val interfaceArgument = Arrays.stream(joinPoint.getArgs()).filter(arg -> arg.getClass() == type).findFirst();

        if (interfaceArgument.isPresent() && filter.validate(interfaceArgument.get()) ) {
            joinPoint.proceed();
        }
    }

}
