package com.alten.shop.controllers.api;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.request.ProductUpdateReqDto;
import com.alten.shop.dto.response.ErrorDto;
import com.alten.shop.dto.response.ProductResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Products", description = "Product management APIs")
@SecurityRequirement(name = "bearer-jwt")
public interface ProductApi {

    @Operation(
            summary = "Create product",
            description = "This method allows adding a product.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ProductResDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "302",
                            description = "Product already exist",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid object",
                            content = {
                                    @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = String.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    @PreAuthorize("@authorizationService.isAdmin()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResDto> createProduct(@RequestBody ProductReqDto dto);

    @Operation(
            summary = "Retrieve the paginated list of products.",
            description = "This endpoint allows retrieving a paginated list of products from the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved paginated list of products.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductResDto.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(name = "page", description = "Page number (0-based).", schema = @Schema(type = "integer", defaultValue = "0")),
                    @Parameter(name = "size", description = "Number of items per page.", schema = @Schema(type = "integer", defaultValue = "20")),
                    @Parameter(name = "sort", description = "Sorting criteria in the format: property(,asc|desc). Default is 'createdAt,desc'.", schema = @Schema(type = "string", example = "createdAt,desc"))
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<ProductResDto>> getAllProducts(
            @Parameter(hidden = true) Pageable pageable
    );


    @Operation(
            summary = "Search by ID",
            description = "This method allows searching for a product by ID.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH, name = "productId",
                            description = "ID of product to return"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been found in the database."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No product was found in the database with the provided ID.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResDto> getProductById(@PathVariable("productId") Long id);

    @Operation(
            summary = "Update product by ID",
            description = "Update an existing product by ID.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "The ID of the product to be updated",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The product update details",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductUpdateReqDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The product has been updated successfully.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ProductResDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request object.",
                            content = {
                                    @Content(
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = String.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product not found.",
                            content = @Content
                    )
            }
    )
    @PreAuthorize("@authorizationService.isAdmin()")
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResDto> updateProduct( @PathVariable Long id,
                                                  @RequestBody ProductUpdateReqDto request);

    @Operation(
            summary = "Delete a product",
            description = "This method allows deleting a product by ID.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "productId",
                            description = "Product ID to delete"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "The product has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No product was found in the database with the provided ID.",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ErrorDto.class
                                            )
                                    )
                            }
                    )
            }
    )
    @PreAuthorize("@authorizationService.isAdmin()")
    @DeleteMapping(value = "/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id);

}
