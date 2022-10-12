package com.prss.order.manager.domain.dao;

import com.prss.order.manager.domain.enums.OrderStepEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "message")
public class Messages {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", nullable = false)
    private Restaurant idRestaurant;
    @Column(name = "order_step_message", nullable = false)
    private OrderStepEnum messageType;
    @Column( nullable = false)
    private String message;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
