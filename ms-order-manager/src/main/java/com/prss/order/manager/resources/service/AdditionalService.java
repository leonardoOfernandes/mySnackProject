package com.prss.order.manager.resources.service;
import com.prss.order.manager.domain.dao.Additional;
import com.prss.order.manager.domain.dao.Ingredients;
import com.prss.order.manager.domain.dao.OrderItems;
import com.prss.order.manager.domain.dto.order.item.additional.AdditionalToOrderItems;
import com.prss.order.manager.domain.dto.order.item.additional.PostAdditionalRequest;
import com.prss.order.manager.domain.dto.order.item.additional.PutAdditionalRequest;
import com.prss.order.manager.resources.respository.AdditionalRepository;
import com.prss.order.manager.resources.respository.IngredientRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class AdditionalService {
    @Autowired
    private AdditionalRepository additionalRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OrderItemsService orderItemsService;

    public void addAdditional(PostAdditionalRequest request) throws ObjectNotFoundException {
        Additional additional = request.toDomain();
        additional.setIdIngredient(ingredientService.findIngredient(request.getIdIngredient()));
        additional.setIdOrderitem(orderItemsService.findOrderItem(request.getIdOrderitem()));
        additionalRepository.save(additional);
    }

    public void deleteAdditional(Integer id) throws ObjectNotFoundException {
        additionalRepository.delete(findAdditional(id));
    }

    public Additional findAdditional(Integer id) throws ObjectNotFoundException {
        Additional item = additionalRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Additional.class.getName()));;
        return item;
    }

    public List<Additional> listAdditionals() {
        return additionalRepository.findAll();
    }

    public void addAdditionalsToOrderItem(List<AdditionalToOrderItems> additionals, OrderItems orderItem){
        additionals.forEach(additional -> {
            Additional additionalDomain= additional.toDomain();
            try {
                additionalDomain.setIdOrderitem(orderItem);
                additionalDomain.setIdIngredient(ingredientService.findIngredient(additional.getIdIngredient()));
                additionalRepository.save(additionalDomain);
            } catch (ObjectNotFoundException e) {
                log.error("Error when find ingredient",e);
            }
        });
    }
}
