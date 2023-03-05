package com.fragile.inventory_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String skuCode ;

    private Integer quantity;

}
