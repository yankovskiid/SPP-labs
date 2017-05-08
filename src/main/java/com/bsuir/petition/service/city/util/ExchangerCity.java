package com.bsuir.petition.service.city.util;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;

public interface ExchangerCity {
    City getCity(CityDTO cityDTO, long id) throws CountryNotFoundException;
    City getCity(CityDTO cityDTO) throws CountryNotFoundException;
}
