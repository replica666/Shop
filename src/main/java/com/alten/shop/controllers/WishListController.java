package com.alten.shop.controllers;

import com.alten.shop.controllers.api.WishListApi;
import com.alten.shop.dto.response.WishListResDto;
import com.alten.shop.service.Impl.WishListServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishListController implements WishListApi {
    private final WishListServiceImpl wishListService;


    @GetMapping
    public ResponseEntity<WishListResDto> getWishList() {
        return ResponseEntity.ok(wishListService.getCurrentUserWishList());
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity<WishListResDto> addToWishList(@PathVariable Long productId) {
        return ResponseEntity.ok(wishListService.addToWishList(productId));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<WishListResDto> removeFromWishList(@PathVariable Long productId) {
        return ResponseEntity.ok(wishListService.removeFromWishList(productId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearWishList() {
        wishListService.clearWishList();
        return ResponseEntity.noContent().build();
    }
}
