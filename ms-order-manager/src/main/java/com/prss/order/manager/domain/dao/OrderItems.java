package com.prss.order.manager.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_item", nullable = false)
    private MenuItems idItem;
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order idOrder;
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    @Column( nullable = false)
    private String note;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "idOrderitem")
    private List<Additional> additionals;
}
