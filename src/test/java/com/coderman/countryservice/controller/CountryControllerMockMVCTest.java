package com.coderman.countryservice.controller;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration
//@ComponentScan(basePackages = "com.coderman.countryservice")
@AutoConfigureMockMvc
@SpringBootTest(classes = {CountryControllerMockMVCTest.class})
class CountryControllerMockMVCTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    void getAllCountries() throws Exception {
        List<Country> myCountries = new ArrayList<>();
        myCountries.add(new Country(1,"TR","Ankara"));
        myCountries.add(new Country(2,"USA","Washington DC"));

        when(countryService.getAllCountries()).thenReturn(myCountries);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/countries"))
                .andExpect(status().isFound())
                .andDo(print());

    }

    @Test
    void getACountryById() throws Exception {
        int countryId = 1;
        Country country = new Country(1,"TR","Ankara");
        when(countryService.getACountryById(countryId)).thenReturn(country);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/countries/{id}",countryId))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("countryName").value("TR"))
                .andExpect(MockMvcResultMatchers.jsonPath("countryCapital").value("Ankara"))
                .andDo(print());
    }

    @Test
    void getACountryName() throws Exception {
        String countryName = "TR";
        Country country = new Country(2,"TR","Ankara");
        when(countryService.getACountryByName(countryName)).thenReturn(country);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/countryname").param("name","TR"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("countryName").value("TR"))
                .andExpect(MockMvcResultMatchers.jsonPath("countryCapital").value("Ankara"))
                .andDo(print());
    }

    @Test
    void addCountry() throws Exception {
        Country country = new Country(1,"TR","Ankara");
        when(countryService.addCountry(country)).thenReturn(country);

        String jsonObj = new ObjectMapper().writeValueAsString(country);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/addcountry")
                                                    .content(jsonObj)
                                                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateCountry() throws Exception {
        int countryId = 1;
        Country country = new Country(1,"USA","DC");
        when(countryService.getACountryById(countryId)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);

        String jsonObj = new ObjectMapper().writeValueAsString(country);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/updatecountry{id}",countryId)
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("countryName").value("USA"))
                .andExpect(MockMvcResultMatchers.jsonPath("countryCapital").value("DC"))
                .andDo(print());
    }

    @Test
    void deleteCountry() throws Exception {
        int countryId = 1;
        Country country = new Country(1,"USA","DC");
        when(countryService.getACountryById(countryId)).thenReturn(country);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/delete/{id}",countryId))
                .andExpect(status().isOk());
    }
}