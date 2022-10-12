package com.prss.order.manager.resources.service;
import com.prss.order.manager.domain.dao.*;
import com.prss.order.manager.domain.dto.order.PostOrderRequest;
import com.prss.order.manager.domain.dto.order.PutOrderRequest;
import com.prss.order.manager.domain.dto.order.item.PostOrderItemRequest;
import com.prss.order.manager.domain.dto.order.item.additional.AdditionalToOrderItems;
import com.prss.order.manager.domain.enums.OrderStepEnum;
import com.prss.order.manager.resources.respository.AdditionalRepository;
import com.prss.order.manager.resources.respository.IngredientRepository;
import com.prss.order.manager.resources.respository.OrderItemsRepository;
import com.prss.order.manager.resources.respository.OrderRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemsService orderItemsService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private RestaurantService restaurantService;

    public void createOrder(PostOrderRequest request) throws ObjectNotFoundException {
        Order order = request.toDomain();
        order.setIdClient(clientService.findClient(request.getIdClient()));
        order.setIdRestaurant(restaurantService.findRestaurant(request.getIdClient()));
        order = orderRepository.save(order);
        orderItemsService.addOrderItem(PostOrderItemRequest.builder()
                .orderItem(request.getOrderItems())
                .idOrder(order.getId())
                .build());
        //TODO chain
    }

    public void updateOrder(PutOrderRequest request) throws ObjectNotFoundException {
        Order savedMenuItems = this.findOrder(request.getId());
        //TODO
        // map.updateExcludeNull(request.toDomain(), savedMenuItems);
        orderRepository.save(savedMenuItems);
    }

    public Order findOrder(Integer id) throws ObjectNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Order.class.getName()));;
        return order;
    }

    public List<Order> listOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public void passSteps(Integer id) throws ObjectNotFoundException {
        Order savedMenuItems = this.findOrder(id);
        savedMenuItems.setOrderStep(OrderStepEnum
                .stepForward(savedMenuItems.getOrderStep())
                .toString());
        orderRepository.save(savedMenuItems);
        //TODO chain
    }
}
