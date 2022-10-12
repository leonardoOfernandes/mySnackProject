package com.prss.order.manager.resources.service;

import com.prss.order.manager.domain.dao.Client;
import com.prss.order.manager.resources.respository.ClientRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client findClient(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Client.class.getName()));
    }

    public List<Client> listRestaurant() {
        return repository.findAll();
    }
}

