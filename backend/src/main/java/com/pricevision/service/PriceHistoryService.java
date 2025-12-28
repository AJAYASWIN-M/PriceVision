package com.pricevision.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pricevision.entity.PriceHistory;
import com.pricevision.entity.Product;
import com.pricevision.repository.PriceHistoryRepository;
import com.pricevision.repository.ProductRepository;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // Add daily price (manual entry)
    public PriceHistory addDailyPrice(Long productId, Double price, LocalDate date) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        PriceHistory history = new PriceHistory(product, price, date);
        return priceHistoryRepository.save(history);
    }

    // Get all prices of a product
    public List<PriceHistory> getPriceHistory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return priceHistoryRepository.findByProduct(product);
    }
}
