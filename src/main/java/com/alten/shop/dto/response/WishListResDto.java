package com.alten.shop.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListResDto {

    private Long id;
    private Set<ProductResDto> products;
    private Integer totalItems;

}
