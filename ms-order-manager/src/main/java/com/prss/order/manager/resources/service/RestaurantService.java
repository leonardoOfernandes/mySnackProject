package com.prss.order.manager.resources.service;

import com.prss.order.manager.domain.dao.Restaurant;
import com.prss.order.manager.resources.respository.RestaurantRepository;
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

    public Restaurant findRestaurant(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Restaurant.class.getName()));
    }

    public List<Restaurant> listRestaurant() {
        return repository.findAll();
    }
}
