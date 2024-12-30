package com.alten.shop.service;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.request.ProductUpdateReqDto;
import com.alten.shop.dto.response.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Page<ProductResDto> getAllProducts(Pageable pageable);
    ProductResDto getProductById(Long id);
    ProductResDto createProduct(ProductReqDto requestDto);
    ProductResDto updateProduct(Long id, ProductUpdateReqDto requestDto);
    void deleteProduct(Long id);
}
