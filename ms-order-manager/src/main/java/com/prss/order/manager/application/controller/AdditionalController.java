package com.prss.order.manager.application.controller;
import com.prss.order.manager.domain.dao.Additional;
import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.order.item.PostOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.PutOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.additional.PostAdditionalRequest;
import com.prss.order.manager.domain.dto.order.item.additional.PutAdditionalRequest;
import com.prss.order.manager.resources.service.AdditionalService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/additional")
public class AdditionalController {

    @Autowired
    private AdditionalService service;

    @PostMapping
    private ResponseEntity registerAdditional(@Valid @RequestBody PostAdditionalRequest request) throws ObjectNotFoundException {
        service.addAdditional(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteAdditional(@PathVariable Integer id) throws ObjectNotFoundException {
        service.deleteAdditional(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findAdditional(@PathVariable Integer id) throws ObjectNotFoundException {
        Additional restaurant = service.findAdditional(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/list")
    private ResponseEntity listAdditionals(){
        List<Additional> list = service.listAdditionals();
        return ResponseEntity.ok(list);
    }
}
