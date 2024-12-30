package com.alten.shop.controllers;

import com.alten.shop.controllers.api.CartApi;
import com.alten.shop.dto.request.CartItemReqDto;
import com.alten.shop.dto.response.CartResDto;
import com.alten.shop.service.Impl.CartServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController implements CartApi {

    private final CartServiceImpl cartService;

    @GetMapping
    public ResponseEntity<CartResDto> getCart() {
        return ResponseEntity.ok(cartService.getCurrentUserCart());
    }

    @PostMapping("/items")
    public ResponseEntity<CartResDto> addItemToCart(@Valid @RequestBody CartItemReqDto request) {
        return ResponseEntity.ok(cartService.addItemToCart(request));
    }

    @PatchMapping("/items/{productId}")
    public ResponseEntity<CartResDto> updateCartItemQuantity(
            @PathVariable Long productId,
            @RequestParam @Min(1) Integer quantity) {
        return ResponseEntity.ok(cartService.updateCartItemQuantity(productId, quantity));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartResDto> removeItemFromCart(@PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(productId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
