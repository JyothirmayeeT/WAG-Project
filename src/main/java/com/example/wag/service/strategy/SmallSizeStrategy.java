package com.example.wag.service.strategy;

public class SmallSizeStrategy implements SizeCategoryStrategy {
    public String determineSizeCategory(long population) {
        return population > 0 && population < 1_000_000 ? "SMALL" : null;
    }
}
