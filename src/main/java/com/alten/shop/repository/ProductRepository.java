package com.alten.shop.repository;

import com.alten.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
    Optional<Product> findByCode(String id);

    boolean existsByCode(String code);
}
