package com.coderman.countryservice.controller;

import com.coderman.countryservice.model.Country;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CountryControllerIntegrationTest {

    @Test
    void getAllCountriesIntegrationTest() throws JSONException {

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"countryName\": \"TR\",\n" +
                "        \"countryCapital\": \"Ankara\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"countryName\": \"USA\",\n" +
                "        \"countryCapital\": \"Washington DC\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"countryName\": \"UK\",\n" +
                "        \"countryCapital\": \"London\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"countryName\": \"Italy\",\n" +
                "        \"countryCapital\": \"Rome\"\n" +
                "    }\n" +
                "]";


        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/countries", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected,response.getBody(),false);

    }

    @Test
    void getACountryByIdIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"countryName\": \"TR\",\n" +
                "    \"countryCapital\": \"Ankara\"\n" +
                "}";

        Map<String,String> params = new HashMap<>();
        params.put("id","1");

        TestRestTemplate restTemplate = new TestRestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/countries/{id}", String.class,params);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/countries/{id}", String.class,"1");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    void getACountryNameIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"countryName\": \"TR\",\n" +
                "    \"countryCapital\": \"Ankara\"\n" +
                "}";

        Map<String,String> params = new HashMap<>();
        params.put("name","TR");

        TestRestTemplate restTemplate = new TestRestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/countries/{id}", String.class,params);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/countryname?name={name}", String.class,"TR");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    void addCountryIntegrationTest() throws JSONException {
        Country country = new Country(10,"Russia","Moscow");


        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<>(country,headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/addcountry",request,String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        Assertions.assertEquals(201,response.getStatusCodeValue());

    }

    @Test
    void updateCountryIntegrationTest() throws JSONException {
        Country country = new Country(10,"Japan","Tokyo");

        String expected = "{\n" +
                "    \"id\": 10,\n" +
                "    \"countryName\": \"Japan\",\n" +
                "    \"countryCapital\": \"Tokyo\"\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<>(country,headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/updatecountry/{id}", HttpMethod.PUT,request,String.class,"10");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        Assertions.assertEquals(200,response.getStatusCodeValue());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }

    @Test
    void deleteCountryIntegrationTest() {
        Country country = new Country(10,"Japan","Tokyo");

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Country> request = new HttpEntity<>(country,headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/deletecountry/{id}", HttpMethod.DELETE,request,String.class,"10");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        Assertions.assertEquals(200,response.getStatusCodeValue());
    }
}