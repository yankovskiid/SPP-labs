package com.bsuir.petition.controller.city.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.controller.city.CityController;
import com.bsuir.petition.service.city.CityService;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.exception.SuchCityExistsException;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityControllerImpl implements CityController{

    private CityService cityService;

    @Autowired
    public void setCategoryService(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void addCity(@RequestBody CityDTO cityDTO) throws ServerException, ErrorInputException, SuchCityExistsException {
        cityService.addCity(cityDTO);
    }

    @Override
    public void updateCity(@RequestBody CityDTO cityDTO,
                           @PathVariable long id) throws CityNotFoundException, ErrorInputException, ServerException {
        cityService.updateCity(cityDTO, id);
    }

    @Override
    public void deleteCity(@PathVariable long id) throws ServerException, CityNotFoundException {
        cityService.deleteCity(id);
    }

    @Override
    public CityListDTO getCities() throws ServerException {
        return cityService.getCities();
    }

    @Override
    public CityListDTO getCities(@PathVariable long countryId) throws ServerException, CountryNotFoundException {
        return cityService.getCities(countryId);
    }
}
