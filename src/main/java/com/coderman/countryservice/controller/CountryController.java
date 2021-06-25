package com.coderman.countryservice.controller;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping("/countries")
    public List<Country> getAllCountries(){
            return countryService.getAllCountries();
    }


    @GetMapping("/countries/{id}")
    public Country getACountryById(@PathVariable(value = "id") int id){
        return countryService.getACountryById(id);
    }

    @GetMapping("/countryname")
    public Country getACountryById2(@RequestParam(value = "name") String countryName){
        return countryService.getACountryByName(countryName);
    }

    @PostMapping("addcountry")
    public Country addCountry(@RequestBody Country country){
        Country savedCountry = countryService.addCountry(country);
        return savedCountry;
    }

    @PutMapping("updatecountry")
    public Country updateCountry(@RequestBody Country country){
        Country savedCountry = countryService.addCountry(country);
        return savedCountry;
    }


    @DeleteMapping("deletecountry/{id}")
    public String deleteCountry(@PathVariable(value = "id") int id){
        return countryService.deleteCountry(id);
    }





}
