package com.example.wag.model;

import java.util.List;

public record CountryDTO(String countryCode,
	    String name,
	    List<String> capital,
	    String region,
	    List<String> currencies,
	    List<String> languages,
	    List<String> borders,
	    String sizeCategory) {

}
