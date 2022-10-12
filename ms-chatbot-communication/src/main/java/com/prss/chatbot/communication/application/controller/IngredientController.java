package com.prss.chatbot.communication.application.controller;

import com.prss.registers.domain.dao.Ingredients;
import com.prss.registers.domain.dto.ingredient.PostIngredientRequest;
import com.prss.registers.domain.dto.ingredient.PutIngredientRequest;
import com.prss.registers.resources.service.IngredientService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/Ingredientt")
public class IngredientController {
    @Autowired
    private IngredientService service;

    @PostMapping
    private ResponseEntity registerIngredient(@Valid @RequestBody PostIngredientRequest request){
        service.registerIngredient(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity putIngredient(@Valid @RequestBody PutIngredientRequest request) throws ObjectNotFoundException {
        service.updateIngredient(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity findIngredient(@PathVariable Integer id) throws ObjectNotFoundException {
        Ingredients Ingredient = service.findIngredient(id);
        return ResponseEntity.ok(Ingredient);
    }

    @GetMapping("/list")
    private ResponseEntity listIngredient(){
        List<Ingredients> list = service.listIngredient();
        return ResponseEntity.ok(list);
    }
}
