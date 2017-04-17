package com.bsuir.petition.service.city.util;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.entity.City;

public interface ExchangerCity {
    City getCity(CityDTO cityDTO, long id);
    City getCity(CityDTO cityDTO);
}
