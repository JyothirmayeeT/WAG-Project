package com.example.wag.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.example.wag.exception.CustomApiException;

@Configuration
public class RestTemplateConfig {
	@Value("${restcountries.api.base-url}")
	  private String baseUrl;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		
		ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
		    @Override
		    public boolean hasError(ClientHttpResponse response) throws IOException {
		        return ((HttpStatus) response.getStatusCode()).series() == HttpStatus.Series.CLIENT_ERROR
		            || ((HttpStatus) response.getStatusCode()).series() == HttpStatus.Series.SERVER_ERROR;
		    }

		    @Override
		    public void handleError(ClientHttpResponse response) throws IOException {
		        String responseBody = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
		            .lines()
		            .collect(Collectors.joining("\n"));
		        System.out.println("Response body: " + responseBody);
		        throw new CustomApiException(response.getStatusCode(), responseBody);
		    }
		};

		return builder
                .rootUri(baseUrl) // Set base URL
              //  .errorHandler(errorHandler)
                .build();
	};

}
