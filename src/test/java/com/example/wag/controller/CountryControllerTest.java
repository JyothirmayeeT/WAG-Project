package com.example.wag.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.wag.model.CountryDTO;
import com.example.wag.service.CountryService;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @Test
    void testGetCountry() {
        String code = "US";
        CountryDTO countryDTO = new CountryDTO(
            "US", "United States of America", List.of("Washington, D.C."),
            "Americas", List.of("USD"), List.of("English"), List.of("CAN", "MEX"),
            "LARGE"
        );

        when(countryService.getCountryData(code)).thenReturn(countryDTO);

        ResponseEntity<CountryDTO> response = countryController.getCountry(code);
        
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(countryDTO, response.getBody());
    }

    
}

