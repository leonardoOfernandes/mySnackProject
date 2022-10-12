package com.prss.chatbot.communication.application.controller;

import com.prss.registers.domain.dao.Restaurant;
import com.prss.registers.domain.dto.restaurant.PostRestaurantRequest;
import com.prss.registers.domain.dto.restaurant.PutRestaurantRequest;
import com.prss.registers.resources.service.RestaurantService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @PostMapping
    private ResponseEntity registerRestaurant(@Valid @RequestBody PostRestaurantRequest request){
        service.registerRestaurant(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putRestaurant(@Valid @RequestBody PutRestaurantRequest request) throws ObjectNotFoundException {
        service.updateRestaurant(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findRestaurant(@PathVariable Integer id) throws ObjectNotFoundException {
        Restaurant restaurant = service.findRestaurant(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/list")
    private ResponseEntity listRestaurant(){
        List<Restaurant> list = service.listRestaurant();
        return ResponseEntity.ok(list);
    }
}
