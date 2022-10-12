package com.prss.order.manager.application.controller;
import com.prss.order.manager.domain.dao.Order;
import com.prss.order.manager.domain.dto.order.PostOrderRequest;
import com.prss.order.manager.domain.dto.order.PutOrderRequest;
import com.prss.order.manager.resources.service.OrderService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    private ResponseEntity registerOrder(@Valid @RequestBody PostOrderRequest request) throws ObjectNotFoundException {
        service.createOrder(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putOrder(@Valid @RequestBody PutOrderRequest request) throws ObjectNotFoundException {
        service.updateOrder(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    private ResponseEntity deleteOrder(@Valid @RequestBody PutOrderRequest request) throws ObjectNotFoundException {
        service.updateOrder(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findOrder(@PathVariable Integer id) throws ObjectNotFoundException {
        Order restaurant = service.findOrder(id);
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping("step-forward/{id}")
    private ResponseEntity passStep(@PathVariable Integer id) throws ObjectNotFoundException {
        service.passSteps(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    private ResponseEntity listOrder(){
        List<Order> list = service.listOrder();
        return ResponseEntity.ok(list);
    }
}
