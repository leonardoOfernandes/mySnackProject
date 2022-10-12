package com.prss.order.manager.resources.service;

import com.prss.order.manager.domain.dao.OrderDelivery;
import com.prss.order.manager.domain.dto.orderdelivery.PostOrderDeliveryRequest;
import com.prss.order.manager.resources.respository.OrderDeliveryRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDeliveryService {

    @Autowired
    private OrderDeliveryRepository repository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliverymanService deliverymanService;

    public void createDeliveryOrder(PostOrderDeliveryRequest request) throws ObjectNotFoundException {
        OrderDelivery orderDelivery = request.toDomain();
        orderDelivery.setIdOrder(orderService.findOrder(request.getIdOrder()));
        orderDelivery.setIdDeliveryman(deliverymanService.findDeliveryman(request.getIdDeliveryman()));
        repository.save(orderDelivery);
        //TODO chain
    }

    public OrderDelivery findOrderDelivery(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ OrderDelivery.class.getName()));
    }

    public List<OrderDelivery> listOrderDelivery() {
        return repository.findAll();
    }

    public void registerDeliveredOrder(Integer id) throws ObjectNotFoundException {
        OrderDelivery orderDelivery = findOrderDelivery(id);
        orderDelivery.setIsDelivered(true);
        orderDelivery.setUpdatedAt(LocalDateTime.now());
        orderDelivery.setDeliveryDate(LocalDateTime.now());
        repository.save(orderDelivery);
        //TODO chain
    }
}
