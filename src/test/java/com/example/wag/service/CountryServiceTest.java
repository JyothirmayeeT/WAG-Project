package com.example.wag.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.example.wag.model.CountryDTO;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class CountryServiceMockWebServerTest {

    private MockWebServer mockWebServer;
    private CountryService countryService;

    @BeforeEach
    void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        countryService = new CountryService(new RestTemplate());
    }

    @AfterEach
    void teardown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetCountryData() {
        String countryCode = "US";

        String mockResponse = """
            [{
                "cca2": "US",
                "name": {"common": "United States"},
                "capital": ["Washington, D.C."],
                "region": "Americas",
                "currencies": {"USD": {}},
                "languages": {"en": "English"},
                "borders": ["CAN", "MEX"],
                "population": 330000000
            }]
        """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200));

        String baseUrl = mockWebServer.url("https://restcountries.com/v3.1/alpha/US").toString();
   //     countryService.setBaseUrl(baseUrl); // Assume setBaseUrl is available for testing

        CountryDTO country = countryService.getCountryData(countryCode);

        assertNotNull(country);
        assertEquals("US", country.countryCode());
        assertEquals("United States", country.name());
        assertEquals("LARGE", country.sizeCategory());
    }
}
