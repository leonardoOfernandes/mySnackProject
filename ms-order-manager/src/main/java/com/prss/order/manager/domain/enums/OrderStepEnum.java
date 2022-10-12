package com.prss.order.manager.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum OrderStepEnum {
    DONE,
    ACCEPTED,
    IN_PROGRESS,
    ACCEPTED_BY_DELIVERYMAN,
    DONE_AND_DELIVERY_STARTED,
    ARRIVED;

    public static OrderStepEnum stepForward( String stepEnum){
        List<OrderStepEnum> list =  Arrays.asList(values());
        OrderStepEnum actualStep = toEnum(stepEnum);
        return list.get(actualStep.ordinal() + 1);

    }

    public static OrderStepEnum toEnum(String stepString){
        Optional<OrderStepEnum> optionalEnum =  Arrays.stream(values()).filter(stepEnum -> stepEnum.toString().equals(stepString)).findAny();
        return optionalEnum.orElse(null);
    }
}
