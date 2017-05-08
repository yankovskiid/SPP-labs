package com.bsuir.petition.service.country.impl;

import com.bsuir.petition.bean.dto.country.CountryDTO;
import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.country.CountryService;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.exception.SuchCountryExistsException;
import com.bsuir.petition.service.country.util.CountryDataValidator;
import com.bsuir.petition.service.country.util.DtoExchangerCountry;
import com.bsuir.petition.service.country.util.ExchangerCountry;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDataValidator countryDataValidator;

    private CountryDao countryDao;

    private DtoExchangerCountry dtoExchangerCountry;

    private ExchangerCountry exchangerCountry;

    @Autowired
    public void setCountryDataValidator(CountryDataValidator countryDataValidator) {
        this.countryDataValidator = countryDataValidator;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Autowired
    public void setDtoExchangerCountry(DtoExchangerCountry dtoExchangerCountry) {
        this.dtoExchangerCountry = dtoExchangerCountry;
    }

    @Autowired
    public void setExchangerCountry(ExchangerCountry exchangerCountry) {
        this.exchangerCountry = exchangerCountry;
    }

    @Override
    @Transactional
    public CountryListDTO getCountries() throws ServerException {
        CountryListDTO countryListDTO;
        List<Country> countries;
        try {
            countries = countryDao.getCountries();
            countryListDTO = dtoExchangerCountry.getCountryListDTO(countries);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
        return countryListDTO;
    }

    @Override
    public void deleteCountry(long id) throws CountryNotFoundException, ServerException {

        try {
            Country country;
            country = countryDao.getCountry(id);

            if (country == null) {
                throw new CountryNotFoundException("Category not found!");
            }

            countryDao.deleteCountry(country);
        } catch (HibernateException exception) {
            throw new ServerException("Such category does", exception);
        }
    }

    @Override
    public void updateCountry(CountryDTO countryDTO, long id) throws CountryNotFoundException, ErrorInputException, ServerException {

        countryDataValidator.validate(countryDTO);

        Country country;
        country = exchangerCountry.getCountry(countryDTO, id);
        try {
            countryDao.updateCountry(country);
        } catch (HibernateException exception) {
            throw new CountryNotFoundException("No such category!", exception);
        }
    }

    @Override
    public void addCountry(ShortCountryDTO countryDTO) throws ErrorInputException, ServerException, SuchCountryExistsException {

        countryDataValidator.validate(countryDTO);

        Country country;
        country = exchangerCountry.getCountry(countryDTO);
        try {
            countryDao.addCountry(country);
        } catch (HibernateException exception) {
            throw new SuchCountryExistsException("Such country exist!");
        }
    }
}
