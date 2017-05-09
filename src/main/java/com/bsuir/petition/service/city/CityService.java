package com.bsuir.petition.service.city;


import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.exception.SuchCityExistsException;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;

public interface CityService {
    CityListDTO getCities() throws ServerException;
    CityListDTO getCities(long countryId) throws ServerException, CountryNotFoundException;
    void deleteCity(long id) throws CityNotFoundException, ServerException;
    void updateCity(CityDTO cityDTO, long id) throws CityNotFoundException, ErrorInputException, ServerException, CountryNotFoundException;
    void addCity(CityDTO cityDTO) throws ErrorInputException, ServerException, SuchCityExistsException, CountryNotFoundException;
}
