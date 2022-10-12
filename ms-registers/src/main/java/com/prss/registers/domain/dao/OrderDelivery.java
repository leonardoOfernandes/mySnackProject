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
@Table(name = "order_delivery")
public class OrderDelivery {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_order", nullable = false)
    private Order idOrder;
    @ManyToOne
    @JoinColumn(name = "id_deliveryman", nullable = false)
    private Deliveryman idDeliveryman;
    @Column(name = "delivery_amount", nullable = false)
    private BigDecimal deliveryAmmount;
    @Column(name = "delivered", nullable = false)
    private Boolean isDelivered;
    @Column(name = "delivery_accepted", nullable = false)
    private Boolean deliveryAccepted;
    @Column( nullable = false)
    private Instant estimate;
    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime deliveryDate;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
