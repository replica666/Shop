package com.alten.shop.controllers.api;

import com.alten.shop.dto.request.CartItemReqDto;
import com.alten.shop.dto.response.CartResDto;
import com.alten.shop.dto.response.ErrorDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart", description = "Shopping cart management APIs")
public interface CartApi {

    @Operation(
            summary = "Get current user's cart",
            description = "This method retrieves the current user's shopping cart.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cart retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cart not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    @GetMapping
    ResponseEntity<CartResDto> getCart();

    @Operation(
            summary = "Add item to cart",
            description = "This method allows adding an item to the user's shopping cart.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item added to cart successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request object",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = String.class)
                                    )
                            )
                    )
            }
    )
    @PostMapping("/items")
    ResponseEntity<CartResDto> addItemToCart(@Valid @RequestBody CartItemReqDto request);

    @Operation(
            summary = "Update cart item quantity",
            description = "This method allows updating the quantity of an item in the user's cart.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cart item quantity updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid quantity value",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    @PatchMapping("/items/{productId}")
    ResponseEntity<CartResDto> updateCartItemQuantity(
            @PathVariable Long productId,
            @RequestParam @Min(1) Integer quantity);

    @Operation(
            summary = "Remove item from cart",
            description = "This method removes an item from the user's shopping cart.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item removed successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartResDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item not found in the cart",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/items/{productId}")
    ResponseEntity<CartResDto> removeItemFromCart(@PathVariable Long productId);

    @Operation(
            summary = "Clear cart",
            description = "This method clears the user's shopping cart.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cart cleared successfully"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> clearCart();
}
