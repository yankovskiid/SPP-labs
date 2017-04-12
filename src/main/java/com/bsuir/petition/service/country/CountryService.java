package com.bsuir.petition.service.country;

import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.exception.SuchCountryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;

/**
 * Created by Александр on 12.04.2017.
 */
public interface CountryService {
    CountryListDTO getCountries() throws ServerException;
    void deleteCountry(long id) throws CountryNotFoundException, ServerException;
    void updateCountry(ShortCountryDTO shortCountryDTO, long id) throws CountryNotFoundException, ErrorInputException, ServerException;
    void addCountry(ShortCountryDTO shortCountryDTO) throws ErrorInputException, ServerException, SuchCountryExistsException;
}
