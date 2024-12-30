package com.alten.shop.service.Impl;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.request.ProductUpdateReqDto;
import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.entities.Product;
import com.alten.shop.exceptions.ResourceConflictException;
import com.alten.shop.exceptions.ResourceNotFoundException;
import com.alten.shop.mappers.ProductMapper;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@Transactional
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResDto createProduct(ProductReqDto request) {
        if (productRepository.existsByCode(request.getCode())) {
            throw new ResourceConflictException("Product with code " + request.getCode() + " already exists");
        }

        Product product = productMapper.toEntity(request);
        product = productRepository.save(product);
        return productMapper.toResDto(product);
    }

    public Page<ProductResDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResDto);
    }

    public ProductResDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id.toString()));
    }

    public ProductResDto updateProduct(Long id, ProductUpdateReqDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id.toString()));

        productMapper.updateEntityFromRequest(request, product);
        product = productRepository.save(product);
        return productMapper.toResDto(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", id.toString());
        }
        productRepository.deleteById(id);
    }
}

