package com.bsuir.petition.controller.country.impl;

import com.bsuir.petition.bean.dto.country.CountryDTO;
import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.controller.country.CountryController;
import com.bsuir.petition.service.country.CountryService;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.exception.SuchCountryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryControllerImpl implements CountryController {

    private CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
    @Override
    public void addCountry(@RequestBody CountryDTO countryDTO) throws ServerException, ErrorInputException, SuchCountryExistsException {
        countryService.addCountry(countryDTO);
    }

    @Override
    public void updateCountry(@RequestBody CountryDTO countryDTO, @PathVariable long id) throws ErrorInputException, ServerException, CountryNotFoundException {
        countryService.updateCountry(countryDTO, id);
    }

    @Override
    public void deleteCountry(@PathVariable long id) throws ServerException, CountryNotFoundException {
        countryService.deleteCountry(id);
    }

    @Override
    public CountryListDTO getCountries() throws ServerException {
        return countryService.getCountries();
    }
}
