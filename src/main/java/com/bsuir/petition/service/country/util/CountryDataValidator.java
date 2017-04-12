package com.bsuir.petition.service.country.util;

import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface CountryDataValidator {
    void validate(ShortCountryDTO shortCategoryDTO) throws ErrorInputException;
}
