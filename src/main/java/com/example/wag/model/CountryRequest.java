package com.example.wag.model;

import lombok.Data;

@Data
public class CountryRequest {
    private String name;
    private String capital;
    private String region;
    private long population;
    private String flag;

    // Getters and Setters
}
