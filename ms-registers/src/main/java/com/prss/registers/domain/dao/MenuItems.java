package com.prss.registers.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "menu_items")
public class MenuItems {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant idRestaurant;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @Column(name = "item_amount", nullable = false)
    private BigDecimal itemAmount;
    @Column(nullable = false)
    private String description;
    @Column
    private String image;
    @Column(name = "time_to_do", nullable = false)
    private Instant timeToDO;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idItem")
    private OrderItems orderItems;
}
