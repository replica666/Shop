package com.alten.shop.dto.request;

import com.alten.shop.enums.EInventoryStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateReqDto {
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;
    private EInventoryStatus inventoryStatus;
    private Integer rating;
}
