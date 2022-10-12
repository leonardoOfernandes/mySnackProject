package com.prss.chatbot.communication.resources.service;

import com.prss.registers.domain.dao.Ingredients;
import com.prss.registers.domain.dto.ingredient.PostIngredientRequest;
import com.prss.registers.domain.dto.ingredient.PutIngredientRequest;
import com.prss.registers.resources.mapper.RegisterMappers;
import com.prss.registers.resources.respository.IngredientRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IngredientService {
    @Autowired
    private IngredientRepository repository;
    @Autowired
    private RegisterMappers map;

    public void registerIngredient(PostIngredientRequest request) {
        repository.save(request.toDomain());
    }

    public void updateIngredient(PutIngredientRequest request) throws ObjectNotFoundException {
        Ingredients savedIngredients = this.findIngredient(request.getId());

        map.updateExcludeNull(request.toDomain(), savedIngredients);
        repository.save(savedIngredients);
    }

    public Ingredients findIngredient(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Ingredients.class.getName()));
    }

    public List<Ingredients> listIngredient() {
        return repository.findAll();
    }
}
