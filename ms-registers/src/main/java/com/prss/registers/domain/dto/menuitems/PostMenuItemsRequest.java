package com.prss.registers.domain.dto.menuitems;

import com.prss.registers.domain.dao.MenuItems;
import com.prss.registers.domain.dao.Restaurant;
import com.prss.registers.domain.dto.Domain;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostMenuItemsRequest implements Domain<MenuItems> {
    private Restaurant idRestaurant;
    private String itemName;
    private BigDecimal itemAmount;
    private String description;
    private String image;
    private Instant timeToDO;
    @Override
    public MenuItems toDomain() {
        return MenuItems.builder()
                .idRestaurant(idRestaurant)
                .itemName(itemName)
                .itemAmount(itemAmount)
                .description(description)
                .image(image)
                .timeToDO(timeToDO)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
