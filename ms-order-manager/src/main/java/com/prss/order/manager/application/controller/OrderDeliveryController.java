package com.prss.order.manager.application.controller;

import com.prss.order.manager.domain.dao.OrderDelivery;
import com.prss.order.manager.domain.dto.orderdelivery.PostOrderDeliveryRequest;
import com.prss.order.manager.resources.service.OrderDeliveryService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class OrderDeliveryController {
    @Autowired
    private OrderDeliveryService service;

    @PostMapping
    private ResponseEntity registerOrderDelivery(@Valid @RequestBody PostOrderDeliveryRequest request) throws ObjectNotFoundException {
        service.createDeliveryOrder(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findOrderDelivery(@PathVariable Integer id) throws ObjectNotFoundException {
        OrderDelivery restaurant = service.findOrderDelivery(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/delivered/{id}")
    private ResponseEntity deliveredOrder(@PathVariable Integer id) throws ObjectNotFoundException {
        service.registerDeliveredOrder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    private ResponseEntity listOrderDeliveries(){
        List<OrderDelivery> list = service.listOrderDelivery();
        return ResponseEntity.ok(list);
    }
}
