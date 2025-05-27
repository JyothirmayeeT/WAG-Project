package com.example.wag.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.wag.exception.CustomApiException;
import com.example.wag.model.CountryDTO;
import com.example.wag.model.CountryResponse;
import com.example.wag.service.factory.SizeCategoryFactory;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryService {

    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        super();
        this.restTemplate = restTemplate;
    }

	@Value("${restcountries.api.base-url}")
	private String baseUrl;

	
	
	
    public CountryDTO getCountryData(String code) {
       // String url = "https://restcountries.com/v3.1/alpha/" + code;
        try {
            // Using getForEntity to directly fetch an array of CountryResponse
            // The API returns a JSON array even for a single country code.
            ResponseEntity<CountryResponse[]> responseEntity = restTemplate.getForEntity(baseUrl+""+code, CountryResponse[].class);

            // Check if the response body is not null and contains elements
            if (responseEntity.getBody() != null && responseEntity.getBody().length > 0) {
                // The API returns a list, so we take the first element (assuming 'alpha' returns one country)
                return transformResponse(responseEntity.getBody()[0]);
            }
        } catch (HttpStatusCodeException e) {
            String responseBody = e.getResponseBodyAsString();
            log.error("Error fetching country data for code {}: {} - {}", code, e.getStatusCode(), responseBody);
            throw new CustomApiException(e.getStatusCode(), responseBody);
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching country data for code {}: {}", code, e.getMessage(), e);
            throw new CustomApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage());
        }
        return null;
    }

    private CountryDTO transformResponse(CountryResponse countryResponse) {
        log.debug("Transforming CountryResponse: {}", countryResponse);

        // Safely get currency codes (keys of the map)
        List<String> currencyCodes = Optional.ofNullable(countryResponse.getCurrencies())
                                            .map(Map::keySet)
                                            .map(keys -> keys.stream().collect(Collectors.toList()))
                                            .orElse(List.of());

        // Safely get language names (values of the map)
        List<String> languageNames = Optional.ofNullable(countryResponse.getLanguages())
                                            .map(Map::values)
                                            .map(values -> values.stream().collect(Collectors.toList()))
                                            .orElse(List.of());

        return new CountryDTO(
                countryResponse.getCca2(),
                Optional.ofNullable(countryResponse.getName()).map(CountryResponse.Name::getCommon).orElse("Unknown"),
                Optional.ofNullable(countryResponse.getCapital()).orElse(List.of()),
                countryResponse.getRegion(),
                currencyCodes,
                languageNames,
                Optional.ofNullable(countryResponse.getBorders()).orElse(List.of()),
                getSizeCategory(countryResponse.getPopulation())
        );
    }

    private String getSizeCategory(Long population) {
        return population == null ? "UNKNOWN" : SizeCategoryFactory.getSizeCategory(population);
    }
}