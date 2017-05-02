package com.bsuir.petition.service.city.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.city.CityService;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.exception.SuchCityExistsException;
import com.bsuir.petition.service.city.util.CityDataValidator;
import com.bsuir.petition.service.city.util.DtoExchangerCity;
import com.bsuir.petition.service.city.util.ExchangerCity;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService{

    private CityDataValidator cityDataValidator;

    private CityDao cityDao;

    private DtoExchangerCity dtoExchangerCity;

    private ExchangerCity exchangerCity;

    private CountryDao countryDao;

    @Autowired
    public void setCountryDao(CountryDao countryDao) { this.countryDao = countryDao; }

    @Autowired
    public void setCityDataValidator(CityDataValidator cityDataValidator) {
        this.cityDataValidator = cityDataValidator;
    }

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Autowired
    public void setDtoExchangerCity(DtoExchangerCity dtoExchangerCity) {
        this.dtoExchangerCity = dtoExchangerCity;
    }

    @Autowired
    public void setExchangerCity(ExchangerCity exchangerCity) {
        this.exchangerCity = exchangerCity;
    }

    @Override
    public CityListDTO getCities() throws ServerException {
        CityListDTO cityListDTO;
        List<City> cities;
        try {
            cities = cityDao.getCities();
            cityListDTO = dtoExchangerCity.getCityListDTO(cities);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
        return cityListDTO;
    }

    @Override
    public CityListDTO getCities(long countryId) throws ServerException, CountryNotFoundException {
        Country country = countryDao.getCountry(countryId);
        if (country == null) {
            throw new CountryNotFoundException("Country not found!");
        }

        CityListDTO cityListDTO;
        List<City> cities;
        try {
            cities = cityDao.getCities(countryId);
            cityListDTO = dtoExchangerCity.getCityListDTO(cities);
        } catch (HibernateException exception) {
            throw new ServerException("Server exception!", exception);
        }
        return cityListDTO;
    }

    @Override
    public void deleteCity(long id) throws CityNotFoundException, ServerException {
        try {
            City city;
            city = cityDao.getCityById(id);

            if (city == null) {
                throw new CityNotFoundException("City not found!");
            }

            cityDao.deleteCity(city);
        } catch (HibernateException exception) {
            throw new ServerException("Such city does", exception);
        }
    }

    @Override
    public void updateCity(CityDTO cityDTO, long id) throws CityNotFoundException, ErrorInputException, ServerException {
        cityDataValidator.validate(cityDTO);

        City city;
        city = exchangerCity.getCity(cityDTO, id);
        try {
            cityDao.updateCity(city);
        } catch (HibernateException exception) {
            throw new CityNotFoundException("No such city!", exception);
        }
    }

    @Override
    public void addCity(CityDTO cityDTO) throws ErrorInputException, ServerException, SuchCityExistsException {
        cityDataValidator.validate(cityDTO);

        City city;
        city = exchangerCity.getCity(cityDTO);
        try {
            cityDao.addCity(city);
        } catch (HibernateException exception) {
            throw new SuchCityExistsException("Server exception!", exception);
        }
    }
}
