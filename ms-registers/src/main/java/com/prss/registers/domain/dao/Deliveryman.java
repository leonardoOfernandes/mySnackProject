package com.prss.registers.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "deliveryman")
public class Deliveryman {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(name = "document_number", nullable = false)
    private String documentNumber;
    @Column( nullable = false)
    private String password;
    @Column(name = "postal_code", nullable = false)
    private Integer postalCode;
    @Column(name = "street_name", nullable = false)
    private String streetName;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String city;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "motorcycle_register", nullable = false)
    private String motorcycleRegister;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "idDeliveryman")
    private List<OrderDelivery> orderDelivery;

}
