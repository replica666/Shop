package com.alten.shop.mappers;

import com.alten.shop.dto.response.CartItemResDto;
import com.alten.shop.dto.response.CartResDto;
import com.alten.shop.entities.Cart;
import com.alten.shop.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    @Mapping(target = "total", expression = "java(calculateTotal(cart))")
    @Mapping(target = "totalItems", expression = "java(calculateTotalItems(cart))")
    CartResDto toResponse(Cart cart);

    @Mapping(target = "subtotal", expression = "java(calculateSubtotal(cartItem))")
    CartItemResDto toCartItemResponse(CartItem cartItem);

    default Double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    default Integer calculateTotalItems(Cart cart) {
        return cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    default Double calculateSubtotal(CartItem cartItem) {
        return cartItem.getProduct().getPrice() * cartItem.getQuantity();
    }
}
