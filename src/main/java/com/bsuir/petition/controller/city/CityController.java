package com.bsuir.petition.controller.city;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.exception.SuchCityExistsException;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CityController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/city", method = RequestMethod.POST)
    void addCity(CityDTO cityDTO) throws ServerException, ErrorInputException, SuchCityExistsException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/city/{id}", method = RequestMethod.POST)
    void updateCity(CityDTO cityDTO, long id) throws ErrorInputException, ServerException, CityNotFoundException;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    void deleteCity(long id) throws ServerException, CityNotFoundException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    CityListDTO getCities() throws ServerException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/cities/{countryId}", method = RequestMethod.GET)
    CityListDTO getCities(long countryId) throws ServerException,CountryNotFoundException;
}
