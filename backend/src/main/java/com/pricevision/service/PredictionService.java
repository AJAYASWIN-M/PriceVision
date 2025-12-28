package com.pricevision.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pricevision.entity.PriceHistory;
import com.pricevision.entity.Product;
import com.pricevision.ml.LinearRegressionModel;
import com.pricevision.repository.PriceHistoryRepository;
import com.pricevision.repository.ProductRepository;

@Service
public class PredictionService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Double> predictNext7Days(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<PriceHistory> historyList = priceHistoryRepository.findByProduct(product);

        int n = historyList.size();
        if (n < 2) {
            throw new RuntimeException("Not enough data for prediction");
        }

        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            x.add((double) (i + 1));                  // Day index
            y.add(historyList.get(i).getPrice());    // Price
        }

        LinearRegressionModel model = new LinearRegressionModel();
        model.train(x, y);

        List<Double> predictions = new ArrayList<>();
        for (int i = n + 1; i <= n + 7; i++) {
            predictions.add(model.predict(i));
        }

        return predictions;
    }
}
