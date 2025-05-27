package com.example.wag.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Important: ignores any fields not defined in this POJO
public class CountryResponse {

    private String cca2;
    private Name name;
    private List<String> capital;
    private String region;
    private Map<String, Currency> currencies; // Map of currency code to Currency object
    private Map<String, String> languages;    // Map of language code to language name
    private List<String> borders;
    private Long population;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        private String common;
        private String official;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {
        private String name;
        private String symbol;
    }
}