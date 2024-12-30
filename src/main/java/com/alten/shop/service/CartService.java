package com.alten.shop.service;

import com.alten.shop.dto.request.CartItemReqDto;
import com.alten.shop.dto.response.CartResDto;

public interface CartService {

    CartResDto getCurrentUserCart();

    CartResDto addItemToCart(CartItemReqDto request);

    CartResDto updateCartItemQuantity(Long productId, Integer quantity);

    CartResDto removeItemFromCart(Long productId);

    void clearCart();
}
