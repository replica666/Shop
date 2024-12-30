package com.alten.shop.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartReqDto {
    private Long userId;
    private List<CartItemReqDto> items;
}
