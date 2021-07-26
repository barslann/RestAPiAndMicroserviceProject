package com.coderman.countryservice.service;

import com.coderman.countryservice.model.Country;
import com.coderman.countryservice.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Country getACountryById(int id){
        return countryRepository.findById(id).get();
    }

    public Country getACountryByName(String countryName){
        return countryRepository.findByCountryName(countryName);
    }

    public Country addCountry(Country country){
        return countryRepository.save(country);
    }

    public Country updateCountry(Country country){
        return countryRepository.save(country);

    }

    public String deleteCountry(Country country){
        countryRepository.delete(country);
        return "Country deleted!!!";
    }


}
