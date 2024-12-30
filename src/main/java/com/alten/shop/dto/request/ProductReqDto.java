package com.alten.shop.dto.request;

import com.alten.shop.enums.EInventoryStatus;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReqDto {

    @NotNull(message = "Code cannot be null")
    @NotEmpty(message = "Code cannot be empty")
    @NotBlank(message = "Code cannot be blank")
    String code;

    @NotBlank(message = "Image URL or path cannot be blank")
    String name;

    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    String description;

    @Positive(message = "Price must be a positive number")
    double price;

    @PositiveOrZero(message = "Quantity must be a non-negative number")
    int quantity;

    @NotNull(message = "Inventory status cannot be null")
    EInventoryStatus inventoryStatus;

    @NotBlank(message = "Category cannot be blank")
    String category;

    @NotBlank(message = "Image URL or path cannot be blank")
    String image;

    @Min(value = 1, message = "Rating must be a positive integer between 1 and 5")
    @Max(value = 5, message = "Rating cannot exceed 5")
    int rating;

    @NotNull(message = "Code cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    String internalReference;
}
