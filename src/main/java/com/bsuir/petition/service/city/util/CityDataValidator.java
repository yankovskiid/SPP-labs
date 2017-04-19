package com.bsuir.petition.service.city.util;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface CityDataValidator {
    void validate(CityDTO cityDTO) throws ErrorInputException;
}
