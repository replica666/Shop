package com.alten.shop.service.Impl;


import com.alten.shop.dto.request.CartItemReqDto;
import com.alten.shop.dto.response.CartResDto;
import com.alten.shop.entities.Cart;
import com.alten.shop.entities.CartItem;
import com.alten.shop.entities.Product;
import com.alten.shop.entities.User;
import com.alten.shop.exceptions.BadRequestException;
import com.alten.shop.exceptions.ResourceNotFoundException;
import com.alten.shop.mappers.CartMapper;
import com.alten.shop.repository.CartRepository;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public CartResDto getCurrentUserCart() {
        Cart cart = getOrCreateCart();
        return cartMapper.toResponse(cart);
    }

    public CartResDto addItemToCart(CartItemReqDto request) {
        Cart cart = getOrCreateCart();
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", request.getProductId().toString()));

        if (product.getQuantity() < request.getQuantity()) {
            throw new BadRequestException("Insufficient stock");
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cart.getItems().add(newItem);
        }

        cart = cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }

    public CartResDto updateCartItemQuantity(Long productId, Integer quantity) {
        Cart cart = getOrCreateCart();
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item", productId.toString()));

        if (item.getProduct().getQuantity() < quantity) {
            throw new BadRequestException("Insufficient stock");
        }

        item.setQuantity(quantity);
        cart = cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }

    public CartResDto removeItemFromCart(Long productId) {
        Cart cart = getOrCreateCart();
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cart = cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }

    public void clearCart() {
        Cart cart = getOrCreateCart();
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getCart() == null) {
            Cart newCart = Cart.builder()
                    .user(currentUser)
                    .items(new ArrayList<>())
                    .build();
            currentUser.setCart(newCart);
            return cartRepository.save(newCart);
        }
        return currentUser.getCart();
    }
}
