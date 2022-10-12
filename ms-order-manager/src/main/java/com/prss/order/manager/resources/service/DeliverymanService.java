package com.prss.order.manager.resources.service;


import com.prss.order.manager.domain.dao.Deliveryman;
import com.prss.order.manager.domain.dto.deliveryman.PostDeliverymanRequest;
import com.prss.order.manager.domain.dto.deliveryman.PutDeliverymanRequest;
import com.prss.order.manager.resources.mapper.RegisterMappers;
import com.prss.order.manager.resources.respository.DeliverymanRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeliverymanService {
    @Autowired
    private DeliverymanRepository repository;
    @Autowired
    private RegisterMappers map;

    public void registerDeliveryman(PostDeliverymanRequest request) {
        repository.save(request.toDomain());
    }

    public void updateDeliveryman(PutDeliverymanRequest request) throws ObjectNotFoundException {
        Deliveryman savedDeliveryman = this.findDeliveryman(request.getId());

        map.updateExcludeNull(request.toDomain(), savedDeliveryman);
        repository.save(savedDeliveryman);
        repository.save(request.toDomain());
    }

    public Deliveryman findDeliveryman(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Deliveryman.class.getName()));
    }

    public List<Deliveryman> listDeliveryman() {
        return repository.findAll();
    }
}
