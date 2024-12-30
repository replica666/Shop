package com.alten.shop.entities;

import com.alten.shop.entities.shared.SharedEntity;
import com.alten.shop.enums.EInventoryStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String code;

    @Column(nullable = false)
    String name;

    String description;

    String image;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    double price;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false, unique = true)
    String internalReference;

    Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EInventoryStatus inventoryStatus;

    int rating;
}
