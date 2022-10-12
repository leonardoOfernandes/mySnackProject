package com.prss.order.manager.application.controller;

import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.order.item.PostOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.PutOrderItemRequest;
import com.prss.order.manager.resources.service.OrderItemsService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order-item")
public class OrderItemsController {
    @Autowired
    private OrderItemsService service;

    @PostMapping
    private ResponseEntity registerRestaurant(@Valid @RequestBody PostOrderItemRequest request) throws ObjectNotFoundException {
        service.addOrderItem(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putRestaurant(@Valid @RequestBody PutOrderItemRequest request) throws ObjectNotFoundException {
        service.updateOrderItem(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findOrderItem(@PathVariable Integer id) throws ObjectNotFoundException {
        OrderItems restaurant = service.findOrderItem(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/list")
    private ResponseEntity listRestaurant(){
        List<OrderItems> list = service.listOrderItem();
        return ResponseEntity.ok(list);
    }
}
