package com.alten.shop.service;

import com.alten.shop.dto.response.WishListResDto;

public interface WishListService {

    WishListResDto getCurrentUserWishList();

    WishListResDto addToWishList(Long productId);

    WishListResDto removeFromWishList(Long productId);

    void clearWishList();
}
