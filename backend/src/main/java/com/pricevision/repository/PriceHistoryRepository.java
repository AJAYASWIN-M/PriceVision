package com.pricevision.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pricevision.entity.PriceHistory;
import com.pricevision.entity.Product;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {

    // Get all prices for a product
    List<PriceHistory> findByProduct(Product product);
}
