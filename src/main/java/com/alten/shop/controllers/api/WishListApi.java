package com.alten.shop.controllers.api;

import com.alten.shop.dto.response.ErrorDto;
import com.alten.shop.dto.response.WishListResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "WishList", description = "Wishlist management APIs")
public interface WishListApi {

    @Operation(
            summary = "Get current user's wishlist",
            description = "This method retrieves the current user's wishlist.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Wishlist retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishListResDto.class)
                            )
                    )
            }
    )
    @GetMapping
    ResponseEntity<WishListResDto> getWishList();

    @Operation(
            summary = "Add product to wishlist",
            description = "This method allows adding a product to the user's wishlist.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product added to wishlist successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishListResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    @PostMapping("/products/{productId}")
    ResponseEntity<WishListResDto> addToWishList(@PathVariable Long productId);

    @Operation(
            summary = "Remove product from wishlist",
            description = "This method removes a product from the user's wishlist.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product removed from wishlist successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = WishListResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found in the wishlist",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/products/{productId}")
    ResponseEntity<WishListResDto> removeFromWishList(@PathVariable Long productId);

    @Operation(
            summary = "Clear wishlist",
            description = "This method clears the user's wishlist.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Wishlist cleared successfully"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> clearWishList();
}

