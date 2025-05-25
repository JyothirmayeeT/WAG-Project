package com.example.wag.service.strategy;

public class LargeSizeStrategy implements SizeCategoryStrategy {
    public String determineSizeCategory(long population) {
        return population > 10_000_000 ? "LARGE" : null;
    }
}
