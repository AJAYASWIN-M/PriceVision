package com.pricevision.ml;

import java.util.List;

public class LinearRegressionModel {

    private double slope;
    private double intercept;

    // Train model
    public void train(List<Double> x, List<Double> y) {
        int n = x.size();

        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumXX += x.get(i) * x.get(i);
        }

        slope = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }

    // Predict price
    public double predict(double x) {
        return slope * x + intercept;
    }
}
