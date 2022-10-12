package com.prss.login.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "additional")
public class Additional {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_ingredient", nullable = false)
    private Ingredients idIngredient;
    @ManyToOne
    @JoinColumn(name = "id_order_item", nullable = false)
    private OrderItems idOrderitem;
    @Column(name = "additional_amount", nullable = false)
    private BigDecimal additionalAmount;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

