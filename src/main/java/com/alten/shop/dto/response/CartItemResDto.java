package com.alten.shop.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResDto {

    private Long id;
    private ProductResDto product;
    private Integer quantity;
    private Double subtotal;

}
