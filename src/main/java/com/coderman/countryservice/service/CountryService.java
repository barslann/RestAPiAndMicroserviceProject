package com.coderman.countryservice.service;

import com.coderman.countryservice.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {
    static Map<Integer, Country> countryIdMap;

    public CountryService() {
        countryIdMap = new HashMap<>();
        countryIdMap.put(1, new Country(1,"Turkey", "Ankara"));
        countryIdMap.put(2, new Country(2,"USA", "Washington DC"));
        countryIdMap.put(3, new Country(3,"UK", "London"));
    }


    public List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>(countryIdMap.values());
        return countries;
    }

    public Country getACountryById(int id){
        return countryIdMap.get(id);
    }

    public Country getACountryByName(String countryName){
        for(Map.Entry<Integer,Country> country:countryIdMap.entrySet()){
            if(country.getValue().getCountryName().equals(countryName)){
                return country.getValue();
            }
        }
        return null;
    }

    public Country addCountry(Country country){
        country.setId(getMaxId());
        countryIdMap.put(country.getId(),country);
        return country;
    }

    public Country updateCountry(Country country){
        if(country.getId() > 0)  countryIdMap.put(country.getId(),country);
        return country;
    }

    public String deleteCountry(int id){
        countryIdMap.remove(id);
        return "Country deleted";
    }

    private int getMaxId(){
        int max= 0;
        for(int id: countryIdMap.keySet()){
            if(id > max) max = id;
        }

        return max+1;
    }


}
