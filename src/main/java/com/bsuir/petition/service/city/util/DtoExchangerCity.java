package com.bsuir.petition.service.city.util;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.bean.entity.City;

import java.util.List;

public interface DtoExchangerCity {
    CityDTO getCityDTO(City city);
    CityListDTO getCityListDTO(List<City> cities);
}
