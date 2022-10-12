package com.prss.registers.application.controller;

import com.prss.registers.domain.dao.Deliveryman;
import com.prss.registers.domain.dto.deliveryman.PostDeliverymanRequest;
import com.prss.registers.domain.dto.deliveryman.PutDeliverymanRequest;
import com.prss.registers.resources.service.DeliverymanService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliveryman")
public class DeliverymanController {
    @Autowired
    private DeliverymanService service;

    @PostMapping
    private ResponseEntity registerDeliveryman(@Valid @RequestBody PostDeliverymanRequest request){
        service.registerDeliveryman(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putDeliveryman(@Valid @RequestBody PutDeliverymanRequest request) throws ObjectNotFoundException {
        service.updateDeliveryman(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findDeliveryman(@PathVariable Integer id) throws ObjectNotFoundException {
        Deliveryman Deliveryman = service.findDeliveryman(id);
        return ResponseEntity.ok(Deliveryman);
    }

    @GetMapping("/list")
    private ResponseEntity listDeliveryman(){
        List<Deliveryman> list = service.listDeliveryman();
        return ResponseEntity.ok(list);
    }
}
