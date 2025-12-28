package com.pricevision.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.Id;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private Double currentPrice;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String productUrl;

    private LocalDateTime createdAt;

    // ================= CONSTRUCTORS =================

    public Product() {
        // Default constructor required by JPA
    }

    public Product(String productName, String platform, Double currentPrice,
                   String imageUrl, String productUrl) {
        this.productName = productName;
        this.platform = platform;
        this.currentPrice = currentPrice;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.createdAt = LocalDateTime.now();
    }

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getPlatform() {
        return platform;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ================= SETTERS =================

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
