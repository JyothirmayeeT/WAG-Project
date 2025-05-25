Here's the README content in a plain text format, suitable for a .txt file:

Country Information API

This API provides an endpoint to retrieve information about a country using its 2-letter ISO country code.

Table of Contents

Features
Technologies Used
Getting Started
Prerequisites
Installation
API Endpoint
Error Handling


Retrieve country details by a 2-letter ISO code.
Input validation for the country code.
Technologies Used

Java: Programming language.
Spring Boot: Framework for building the API.
Maven: Dependency management and build automation.
Jackson: For JSON processing (JsonProcessingException, JsonMappingException).
Jakarta Validation: For input validation (@Validated, @Size, @Pattern).
Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

Prerequisites

Java Development Kit (JDK) 17 or higher: Make sure you have a compatible JDK installed.
Maven: Ensure Maven is installed and configured in your environment.
Git: For cloning the repository.
Installation

Clone the repository:

git clone <repository-url>
cd wag
Build the project with Maven:

mvn clean install
Run the application:

mvn spring-boot:run
The application will start on port 8080 by default (or as configured in application.properties).

API Endpoint

Get Country Information

Retrieves detailed information for a specified country code.

URL: /country/{code}

Method: GET

Path Parameters:

code (String, required): The 2-letter ISO country code (e.g., "US" for United States, "FR" for France).
Example Request:

GET http://localhost:8080/country/US
Example Success Response (Status: 200 OK):

JSON

{
    "name": "United States",
    "capital": "Washington D.C.",
    "region": "Americas",
    "population": 331002651,
    "flagUrl": "https://restcountries.com/data/usa.svg"
    // ... other country specific fields from CountryDTO
}
(Note: The actual fields in CountryDTO would depend on its definition in com.example.wag.model.CountryDTO.)

Example Not Found Response (Status: 404 Not Found):
If the country code is valid but no data is found for it (e.g., an invalid but correctly formatted country code).

(No response body)
Error Handling

The API includes validation for the code path variable:

Invalid Code Length (Status: 400 Bad Request): If the code is not exactly 2 characters long.
JSON

{
    "timestamp": "2023-10-27T10:00:00.000+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "getCountry.code: Code must be exactly 2 characters",
    "path": "/country/USA"
}
Invalid Code Characters (Status: 400 Bad Request): If the code contains characters other than letters.
JSON

{
    "timestamp": "2023-10-27T10:00:00.000+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "getCountry.code: Code must contain only letters",
    "path": "/country/U1"
}
