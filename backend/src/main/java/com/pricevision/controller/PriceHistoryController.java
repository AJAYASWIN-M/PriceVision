package com.pricevision.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pricevision.entity.PriceHistory;
import com.pricevision.service.PriceHistoryService;

@RestController
@RequestMapping("/api/price-history")
@CrossOrigin(origins = "*")
public class PriceHistoryController {

    @Autowired
    private PriceHistoryService priceHistoryService;

    // Add daily price manually
    @PostMapping("/add")
    public PriceHistory addPrice(@RequestBody Map<String, String> request) {

        Long productId = Long.parseLong(request.get("productId"));
        Double price = Double.parseDouble(request.get("price"));
        LocalDate date = LocalDate.parse(request.get("date"));

        return priceHistoryService.addDailyPrice(productId, price, date);
    }

    // Get price history of a product
    @GetMapping("/{productId}")
    public List<PriceHistory> getHistory(@PathVariable Long productId) {
        return priceHistoryService.getPriceHistory(productId);
    }
}
