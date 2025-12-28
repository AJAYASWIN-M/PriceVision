package com.pricevision.controller;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pricevision.entity.Product;
import com.pricevision.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ===============================
    // PREVIEW PRODUCT FROM URL
    // ===============================
    @PostMapping("/preview")
    public Product previewProduct(@RequestBody Map<String, String> request) {

        String url = request.get("url");
        Product product = new Product();

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                    .referrer("https://www.google.com")
                    .timeout(15000)
                    .get();

            // ---------- PRODUCT NAME ----------
            String title = doc.select("meta[property=og:title]").attr("content");
            if (title == null || title.isEmpty()) {
                title = doc.title();
            }
            product.setProductName(
                    (title == null || title.isEmpty()) ? "Product Preview" : cleanTitle(title)
            );

            // ---------- IMAGE ----------
            String image = doc.select("meta[property=og:image]").attr("content");
            product.setImageUrl(image);

            // ---------- PLATFORM ----------
            String platform = detectPlatform(url);
            product.setPlatform(platform);

            // ---------- PRICE (BEST-EFFORT) ----------
            Double price = extractPrice(doc, platform);
            product.setCurrentPrice(price);

            // ---------- URL ----------
            product.setProductUrl(url);

        } catch (Exception e) {
            // Graceful fallback
            product.setProductName("Preview unavailable");
            product.setPlatform(detectPlatform(url));
            product.setCurrentPrice(0.0);
            product.setProductUrl(url);
        }

        return product; // NOT SAVED
    }

    // ===============================
    // SAVE PRODUCT
    // ===============================
    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // ===============================
    // HELPER METHODS
    // ===============================
    private String detectPlatform(String url) {
        if (url == null) return "Unknown";
        if (url.contains("amazon")) return "Amazon";
        if (url.contains("flipkart")) return "Flipkart";
        return "Unknown";
    }

    private String cleanTitle(String title) {
        return title
                .replace("| Amazon.in", "")
                .replace("| Flipkart", "")
                .trim();
    }

    // 🔥 PRICE EXTRACTION (BEST-EFFORT)
    private Double extractPrice(Document doc, String platform) {

        try {
            String priceText = "";

            if ("Amazon".equals(platform)) {
                priceText = doc.select("span.a-price-whole").first().text();
            } else if ("Flipkart".equals(platform)) {
                priceText = doc.select("div._30jeq3").first().text();
            }

            if (priceText == null || priceText.isEmpty()) {
                return 0.0;
            }

            // Remove currency symbols & commas
            priceText = priceText.replaceAll("[^0-9]", "");
            return Double.parseDouble(priceText);

        } catch (Exception e) {
            return 0.0; // Price unavailable
        }
    }
}
