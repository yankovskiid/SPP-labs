package com.bsuir.petition.service.city.util.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.service.city.util.CityDataValidator;
import com.bsuir.petition.service.exception.ErrorInputException;
import org.springframework.stereotype.Component;

@Component
public class CityDataValidatorImpl implements CityDataValidator {
    @Override
    public void validate(CityDTO cityDTO) throws ErrorInputException {
        validateString(cityDTO.getName());

//        long countryId = cityDTO.getCountry();
//        validateString(country);
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
