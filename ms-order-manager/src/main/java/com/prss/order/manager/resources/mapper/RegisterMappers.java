
package com.prss.order.manager.resources.mapper;


import com.prss.order.manager.domain.dao.Deliveryman;
import com.prss.order.manager.domain.dao.Ingredients;
import com.prss.order.manager.domain.dao.MenuItems;
import com.prss.order.manager.domain.dao.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RegisterMappers {

    @Mappings({
            @Mapping(target = "menuItems", ignore = true),
            @Mapping(target = "messages", ignore = true),
            @Mapping(target = "orders", ignore = true)})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExcludeNull(Restaurant source, @MappingTarget Restaurant target);
    @Mappings({
            @Mapping(target = "orderDelivery", ignore = true)})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExcludeNull(Deliveryman source, @MappingTarget Deliveryman target);
    @Mappings({
            @Mapping(target = "additional", ignore = true)})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExcludeNull(Ingredients source, @MappingTarget Ingredients target);
}