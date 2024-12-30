package com.alten.shop.dto.response;

import com.alten.shop.enums.EInventoryStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResDto {

    Long id;
    String code;
    String name;
    String description;
    String image;
    String category;
    double price;
    int quantity;
    String internalReference;
    Long shellId;
    EInventoryStatus inventoryStatus;
    int rating;
    Long createdAt;
    Long updatedAt;
}
