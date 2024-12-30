package com.alten.shop.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResDto {

    private Long id;
    private List<CartItemResDto> items;
    private Double total;
    private Integer totalItems;

}
