package com.prss.order.manager.resources.service;
import com.prss.order.manager.domain.dao.Additional;
import com.prss.order.manager.domain.dao.Ingredients;
import com.prss.order.manager.domain.dao.Order;
import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.order.PostOrderRequest;
import com.prss.order.manager.domain.dto.order.item.PostOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.PutOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.additional.AdditionalToOrderItems;
import com.prss.order.manager.resources.respository.AdditionalRepository;
import com.prss.order.manager.resources.respository.IngredientRepository;
import com.prss.order.manager.resources.respository.OrderItemsRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private AdditionalService additionalService;
    @Autowired
    private MenuItemsService menuItemsService;
    @Autowired
    private OrderService orderService;

    public void addOrderItem(PostOrderItemRequest request) throws ObjectNotFoundException {
        Order order = orderService.findOrder(request.getIdOrder());
        addOrderItemsToOrder(request, order);

    }

    public void updateOrderItem(PutOrderItemRequest request) throws ObjectNotFoundException {
        OrderItems savedMenuItems = this.findOrderItem(request.getId());
        //TODO
        // map.updateExcludeNull(request.toDomain(), savedMenuItems);
        savedMenuItems = orderItemsRepository.save(savedMenuItems);
        additionalService.addAdditionalsToOrderItem(request.getAdditionals(), savedMenuItems);

    }

    public OrderItems findOrderItem(Integer id) throws ObjectNotFoundException {
        OrderItems item = orderItemsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ OrderItems.class.getName()));;
        return item;
    }

    public List<OrderItems> listOrderItem() {
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        return orderItemsList;
    }
    private void addOrderItemsToOrder(PostOrderItemRequest request, Order order) {
        request.getOrderItem().forEach(item ->{
            OrderItems itemDomain = item.toDomain();
            itemDomain.setIdOrder(order);
            try {
                itemDomain.setIdItem(menuItemsService.findMenuItems(item.getIdItem()));
                orderItemsRepository.save(itemDomain);
                additionalService.addAdditionalsToOrderItem(item.getAdditionals(),itemDomain);
            } catch (ObjectNotFoundException e) {
                log.error("Error when find ingredient",e);
            }

        });
    }
}
