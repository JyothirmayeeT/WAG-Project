package com.example.wag.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse {
	//@JsonProperty("")
	//private String countryCode;
	@JsonProperty("name")
    private String name;
  /*  private String capital;
    private String region;
    private List<String> currencies;
    private List<String> languages;
    private List<String> borders;
    private long population;
    //private String sizeCategory;*/
}


