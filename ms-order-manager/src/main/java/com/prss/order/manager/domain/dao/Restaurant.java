package com.prss.order.manager.domain.dao;

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
@Table(name = "restaurant")
public class Restaurant {
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
    @Column(nullable = false)
    private String type;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "idRestaurant")
    private List<MenuItems> menuItems;
    @JsonIgnore
    @OneToMany(mappedBy = "idRestaurant")
    private List<Messages> messages;
    @JsonIgnore
    @OneToMany(mappedBy = "idRestaurant")
    private List<Order> orders;
}
