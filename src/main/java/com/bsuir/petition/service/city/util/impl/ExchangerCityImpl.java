package com.bsuir.petition.service.city.util.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.city.util.ExchangerCity;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public City getCity(CityDTO cityDTO, long id) throws CountryNotFoundException {
        City city = cityDao.getCityById(id);
        city.setName(cityDTO.getName());
        try {
            Country country = countryDao.getCountry(cityDTO.getCountry());
            city.setCountry(country);
        } catch (Exception e) {
            throw new CountryNotFoundException("Country not found!");
        }
        return city;
    }

    @Override
    public City getCity(CityDTO cityDTO) throws CountryNotFoundException {
        City city = new City();
        city.setName(cityDTO.getName());
        try {
            Country country = countryDao.getCountry(cityDTO.getCountry());
            city.setCountry(country);
        } catch (Exception e) {
            throw new CountryNotFoundException("Country not found!");
        }
        return city;
    }
}
