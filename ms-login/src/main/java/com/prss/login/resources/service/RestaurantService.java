package com.prss.login.resources.service;

import com.prss.registers.domain.dao.Restaurant;
import com.prss.registers.domain.dto.restaurant.PostRestaurantRequest;
import com.prss.registers.domain.dto.restaurant.PutRestaurantRequest;
import com.prss.registers.resources.mapper.RegisterMappers;
import com.prss.registers.resources.respository.RestaurantRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private RegisterMappers map;

    public void registerRestaurant(PostRestaurantRequest request) {
        repository.save(request.toDomain());
    }

    public void updateRestaurant(PutRestaurantRequest request) throws ObjectNotFoundException {
        Restaurant savedRestaurant = this.findRestaurant(request.getId());

        map.updateExcludeNull(request.toDomain(), savedRestaurant);
        repository.save(savedRestaurant);
    }

    public Restaurant findRestaurant(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Restaurant.class.getName()));
    }

    public List<Restaurant> listRestaurant() {
        return repository.findAll();
    }
}
