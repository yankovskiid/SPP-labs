package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Country;

import java.util.List;

/**
 * Created by Александр on 12.04.2017.
 */
public interface CountryDao {
    Country getCountry(long id);
    Country getCountry(String name);
    List<Country> getCountries();
    void updateCountry(Country country);
    void deleteCountry(Country country);
    void addCountry(Country country);
}
