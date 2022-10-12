package com.prss.registers.application.controller;

import com.prss.registers.domain.dao.Messages;
import com.prss.registers.domain.dto.message.PostMessageRequest;
import com.prss.registers.domain.dto.message.PutMessageRequest;
import com.prss.registers.resources.service.MessageService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService service;

    @PostMapping
    private ResponseEntity registerMessage(@Valid @RequestBody PostMessageRequest request){
        service.registerMessage(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putMessage(@Valid @RequestBody PutMessageRequest request) throws ObjectNotFoundException {
        service.updateMessage(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findMessage(@PathVariable Integer id) throws ObjectNotFoundException {
        Messages Message = service.findMessage(id);
        return ResponseEntity.ok(Message);
    }

    @GetMapping("/list")
    private ResponseEntity listMessage(){
        List<Messages> list = service.listMessage();
        return ResponseEntity.ok(list);
    }
}
