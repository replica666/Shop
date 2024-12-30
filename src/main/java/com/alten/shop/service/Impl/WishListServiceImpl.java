package com.alten.shop.service.Impl;

import com.alten.shop.dto.response.WishListResDto;
import com.alten.shop.entities.*;
import com.alten.shop.exceptions.ResourceNotFoundException;
import com.alten.shop.mappers.WishListMapper;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.repository.WishListRepository;
import com.alten.shop.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Transactional
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    private final WishListMapper wishListMapper;

    @Transactional(readOnly = true)
    public WishListResDto getCurrentUserWishList() {
        WishList wishList = getOrCreateWishList();
        return wishListMapper.toResponse(wishList);
    }

    public WishListResDto addToWishList(Long productId) {
        WishList wishList = getOrCreateWishList();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", productId.toString()));

        wishList.getProducts().add(product);
        wishList = wishListRepository.save(wishList);
        return wishListMapper.toResponse(wishList);
    }

    public WishListResDto removeFromWishList(Long productId) {
        WishList wishList = getOrCreateWishList();
        wishList.getProducts().removeIf(product -> product.getId().equals(productId));
        wishList = wishListRepository.save(wishList);
        return wishListMapper.toResponse(wishList);
    }

    public void clearWishList() {
        WishList wishList = getOrCreateWishList();
        wishList.getProducts().clear();
        wishListRepository.save(wishList);
    }

    private WishList getOrCreateWishList() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getWishList() == null) {
            WishList newWishList = WishList.builder()
                    .user(currentUser)
                    .products(new HashSet<>())
                    .build();
            currentUser.setWishList(newWishList);
            return wishListRepository.save(newWishList);
        }
        return currentUser.getWishList();
    }
}
