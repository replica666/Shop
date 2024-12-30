package com.alten.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemReqDto {

    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private Integer quantity;

}
