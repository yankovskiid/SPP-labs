package com.bsuir.petition.controller.country;

import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.exception.SuchCountryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CountryController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/country", method = RequestMethod.POST)
    void addCountry(ShortCountryDTO shortCountryDTO) throws ServerException, ErrorInputException, SuchCountryExistsException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/country/{id}", method = RequestMethod.POST)
    void updateCountry(ShortCountryDTO shortCountryDTO, long id) throws ErrorInputException, ServerException, CountryNotFoundException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    void deleteCountry(long id) throws ServerException, CountryNotFoundException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    CountryListDTO getCountries() throws ServerException;
}
