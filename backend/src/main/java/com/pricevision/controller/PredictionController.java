package com.pricevision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pricevision.service.PredictionService;

@RestController
@RequestMapping("/api/predict")
@CrossOrigin(origins = "*")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @GetMapping("/{productId}")
    public List<Double> predict(@PathVariable Long productId) {
        return predictionService.predictNext7Days(productId);
    }
}
