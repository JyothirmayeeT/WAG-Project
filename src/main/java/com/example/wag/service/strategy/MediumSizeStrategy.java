package com.example.wag.service.strategy;

public class MediumSizeStrategy implements SizeCategoryStrategy {
    public String determineSizeCategory(long population) {
        return population >= 1_000_000 && population <= 10_000_000 ? "MEDIUM" : null;
    }
}
