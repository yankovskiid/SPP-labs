package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.City;

import java.util.List;

public interface CityDao {
    City getCityByName(String cityName);
    City getCityById(long id);

    void addCity(City city);
    void updateCity(City city);
    void deleteCity(City city);
    List<City> getCities();
    List<City> getCities(long countryId);
}
