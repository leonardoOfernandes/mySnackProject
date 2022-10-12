package com.prss.order.manager.resources.service;

import com.prss.order.manager.domain.dao.Ingredients;
import com.prss.order.manager.resources.respository.IngredientRepository;
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

    public Ingredients findIngredient(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Ingredients.class.getName()));
    }

    public List<Ingredients> listIngredient() {
        return repository.findAll();
    }
}
