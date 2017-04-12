package com.bsuir.petition.service.country.util.impl;

import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.service.country.util.CountryDataValidator;
import com.bsuir.petition.service.exception.ErrorInputException;
import org.springframework.stereotype.Component;

@Component
public class CountryDataValidatorImpl implements CountryDataValidator {
    @Override
    public void validate(ShortCountryDTO shortCategoryDTO) throws ErrorInputException {
        validateString(shortCategoryDTO.getName());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
