package com.example.wag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.wag.exception.CustomApiException;
import com.example.wag.model.CountryDTO;
import com.example.wag.model.CountryResponse;
import com.example.wag.service.factory.SizeCategoryFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryService {

	private final RestTemplate restTemplate;

	public CountryService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public CountryDTO getCountryData(String code) {
		String url = "https://restcountries.com/v3.1/alpha/" + code;
		try {
			ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<>() {
					});

			if (response.getBody() != null && !response.getBody().isEmpty()) {
				return transformResponse(response.getBody().get(0));
			}
		} catch (HttpStatusCodeException e) {
			String responseBody = e.getResponseBodyAsString();
			System.out.println("Error response body: " + responseBody);
			throw new CustomApiException(e.getStatusCode(), responseBody);
			
		}
		return null;
	}

	private CountryDTO transformResponse(Map<String, Object> countryData) {
		return new CountryDTO((String) countryData.get("cca2"),
				(String) ((Map<String, Object>) countryData.get("name")).get("common"),
				getStringList(countryData, "capital"), (String) countryData.get("region"), getCurrencyList(countryData),
				getLanguageList(countryData), getStringList(countryData, "borders"),
				getSizeCategory((Number) countryData.get("population")));
	}

	private List<String> getStringList(Map<String, Object> data, String key) {
		Object value = data.get(key);
		if (value instanceof List<?>) {
			return ((List<?>) value).stream().filter(String.class::isInstance).map(String.class::cast).toList();
		}
		return List.of();
	}

	private List<String> getCurrencyList(Map<String, Object> data) {
		Object value = data.get("currencies");
		if (value instanceof Map<?, ?> currenciesMap) {
			return new ArrayList<>(
					currenciesMap.keySet().stream().filter(String.class::isInstance).map(String.class::cast).toList());
		}
		return List.of();
	}

	private List<String> getLanguageList(Map<String, Object> data) {
		Object value = data.get("languages");
		if (value instanceof Map<?, ?> languagesMap) {
			return new ArrayList<>(
					languagesMap.values().stream().filter(String.class::isInstance).map(String.class::cast).toList());
		}
		return List.of();
	}

	private String getSizeCategory(Number population) {
		return population == null ? "UNKNOWN" : SizeCategoryFactory.getSizeCategory(population.longValue());
	}

}
