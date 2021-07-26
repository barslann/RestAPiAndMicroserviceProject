package com.coderman.countryservice.controller;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries(){
            return new ResponseEntity<>(countryService.getAllCountries(),HttpStatus.FOUND);
    }


    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getACountryById(@PathVariable(value = "id") int id){
        try {
            Country country = countryService.getACountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countryname")
    public ResponseEntity<Country> getACountryName(@RequestParam(value = "name") String countryName){
        try {
            Country country = countryService.getACountryByName(countryName);
            return new ResponseEntity<>(country, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        try{
            country = countryService.addCountry(country);
            return new ResponseEntity<>(country,HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country){
        try{
            Country existCountry = countryService.getACountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            return new ResponseEntity<>(countryService.updateCountry(existCountry),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("deletecountry/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable(value = "id") int id){
        Country country = null;
        try{
            country = countryService.getACountryById(id);
            countryService.deleteCountry(country);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
