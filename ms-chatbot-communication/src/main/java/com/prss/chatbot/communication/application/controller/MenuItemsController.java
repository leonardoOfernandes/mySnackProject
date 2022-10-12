package com.prss.chatbot.communication.application.controller;

import com.prss.registers.domain.dao.MenuItems;
import com.prss.registers.domain.dto.menuitems.PostMenuItemsRequest;
import com.prss.registers.domain.dto.menuitems.PutMenuItemsRequest;
import com.prss.registers.resources.service.MenuItemsService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu-items")
public class MenuItemsController {
    @Autowired
    private MenuItemsService service;
    
    @PostMapping
    private ResponseEntity registerMenuItems(@Valid @RequestBody PostMenuItemsRequest request){
        service.registerMenuItems(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putMenuItems(@Valid @RequestBody PutMenuItemsRequest request) throws ObjectNotFoundException {
        service.updateMenuItems(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findMenuItems(@PathVariable Integer id) throws ObjectNotFoundException {
        MenuItems MenuItems = service.findMenuItems(id);
        return ResponseEntity.ok(MenuItems);
    }

    @GetMapping("/list")
    private ResponseEntity listMenuItems(){
        List<MenuItems> list = service.listMenuItems();
        return ResponseEntity.ok(list);
    }
}
