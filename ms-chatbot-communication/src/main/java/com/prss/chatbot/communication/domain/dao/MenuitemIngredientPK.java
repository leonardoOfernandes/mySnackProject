package com.prss.chatbot.communication.domain.dao;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MenuitemIngredientPK{

    @ManyToOne
    @JoinColumn(name = "menu_item")
    private MenuItems menuitem;

    @ManyToOne
    @JoinColumn(name = "ingredients")
    private Ingredients ingredients;
}
