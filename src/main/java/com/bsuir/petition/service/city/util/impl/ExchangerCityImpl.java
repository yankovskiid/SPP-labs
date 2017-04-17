package com.bsuir.petition.service.city.util.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.city.util.ExchangerCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangerCityImpl implements ExchangerCity{

    private CityDao cityDao;
    private CountryDao countryDao;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) { this.countryDao = countryDao; }

    @Override
    public City getCity(CityDTO cityDTO, long id) {
        City city = cityDao.getCityById(id);
        city.setName(cityDTO.getName());
        Country country = countryDao.getCountry(cityDTO.getCountry());
        city.setCountry(country);
        return city;
    }

    @Override
    public City getCity(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        Country country = countryDao.getCountry(cityDTO.getCountry());
        city.setCountry(country);
        return city;
    }
}
