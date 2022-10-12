
package com.prss.registers.resources.mapper;


import com.prss.registers.domain.dao.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
    @Mappings({
            @Mapping(target = "idRestaurant", ignore = true)})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExcludeNull(Messages source, @MappingTarget Messages target);
    /*@Mappings({
            @Mapping(target = "idRestaurant", ignore = true)})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateExcludeNull(MenuItems toDomain, MenuItems savedMenuItems);*/
}