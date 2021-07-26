package com.coderman.countryservice.service;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.repo.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    @Test
    @Order(1)
    public void getAllCountriesTest(){
        List<Country> myCountries = new ArrayList<>();
        myCountries.add(new Country(1,"TR","Ankara"));
        myCountries.add(new Country(2,"USA","Washington DC"));

        when(countryRepository.findAll()).thenReturn(myCountries);

        Assertions.assertEquals(2,countryService.getAllCountries().size());
    }

    @Test
    @Order(4)
    public void getACountryByIdTest(){
        int countryId = 1;
        Optional<Country> country1 = Optional.of(new Country(1, "TR", "Ankara"));
        when(countryRepository.findById(countryId)).thenReturn(country1);

        Assertions.assertEquals(country1.get(),countryService.getACountryById(1));
    }

    @Test
    @Order(3)
    public void getACountryByName(){
        String countryName = "TR";
        Country country = new Country(1, "TR", "Ankara");

        when(countryRepository.findByCountryName(countryName)).thenReturn(country);

        Assertions.assertEquals(country,countryService.getACountryByName(countryName));
    }

    @Test
    @Order(2)
    public void addACountry(){
        Country country = new Country(1, "TR", "Ankara");

        when(countryRepository.save(country)).thenReturn(country);

        Assertions.assertEquals(country,countryService.addCountry(country));
    }

    @Test
    @Order(6)
    public void deleteACountry(){
        Country country = new Country(1, "TR", "Ankara");
        countryService.deleteCountry(country);

        verify(countryRepository,times(1)).delete(country);

    }
}
