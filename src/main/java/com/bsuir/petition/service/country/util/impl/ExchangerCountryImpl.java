package com.bsuir.petition.service.country.util.impl;

import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.country.util.ExchangerCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangerCountryImpl implements ExchangerCountry {

    private CountryDao countryDao;

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country getCountry(ShortCountryDTO shortCountryDTO, long id) {
        Country country = countryDao.getCountry(id);
        country.setName(shortCountryDTO.getName());
        return country;
    }

    @Override
    public Country getCountry(ShortCountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        return country;
    }
}
