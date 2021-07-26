package com.coderman.countryservice.controller;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CountryControllerTest.class})
class CountryControllerTest {

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;


    @Test
    void getAllCountries() {
        List<Country> myCountries = new ArrayList<>();
        myCountries.add(new Country(1,"TR","Ankara"));
        myCountries.add(new Country(2,"USA","Washington DC"));

        when(countryService.getAllCountries()).thenReturn(myCountries);

        ResponseEntity<List<Country>> res = countryController.getAllCountries();

        assertEquals(HttpStatus.FOUND,res.getStatusCode());
        assertEquals(2,res.getBody().size());
    }

    @Test
    void getACountryById() {
        int countryId = 1;
        Country country = new Country(1,"TR","Ankara");
        when(countryService.getACountryById(countryId)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getACountryById(countryId);

        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(countryId,res.getBody().getId());
    }

    @Test
    void getACountryByName() {
        String countryName = "TR";
        Country country = new Country(1,"TR","Ankara");
        when(countryService.getACountryByName(countryName)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getACountryName(countryName);

        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(countryName, Objects.requireNonNull(res.getBody()).getCountryName());
    }

    @Test
    void addCountry() {
        Country country = new Country(1,"TR","Ankara");
        when(countryService.addCountry(country)).thenReturn(country);

        ResponseEntity<Country> res = countryController.addCountry(country);

        assertEquals(HttpStatus.CREATED,res.getStatusCode());
        assertEquals(country.getId(), Objects.requireNonNull(res.getBody()).getId());

    }

    @Test
    void updateCountry() {
        int countryId = 1;
        Country country = new Country(1,"USA","DC");
        when(countryService.getACountryById(countryId)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);

        ResponseEntity<Country> res = countryController.updateCountry(countryId,country);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(country, Objects.requireNonNull(res.getBody()));
    }

    @Test
    void deleteCountry() {
        int countryId = 1;
        Country country = new Country(1,"USA","DC");
        when(countryService.getACountryById(countryId)).thenReturn(country);

        ResponseEntity<Country> res = countryController.deleteCountry(countryId);
        assertEquals(HttpStatus.OK,res.getStatusCode());

    }
}