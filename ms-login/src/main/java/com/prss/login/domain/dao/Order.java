package com.prss.login.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant idRestaurant;
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client idClient;
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    @Column( nullable = false)
    private String note;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "idOrder")
    private List<OrderItems> orderItems;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idOrder")
    private OrderDelivery orderDelivery;
}
