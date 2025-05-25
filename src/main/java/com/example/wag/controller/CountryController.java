package com.example.wag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wag.model.CountryDTO;
import com.example.wag.model.CountryResponse;
import com.example.wag.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/")
@Validated
public class CountryController {

	@Autowired
	private CountryService service;

	@GetMapping("country/{code}")
    public ResponseEntity<CountryDTO> getCountry(
    		@PathVariable 
    		@Size(min = 2, max = 2, message = "Code must be exactly 2 characters") 
    		@Pattern(regexp = "[a-zA-Z]+", message = "Code must contain only letters") String code){
        CountryDTO country = service.getCountryData(code);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }

}
