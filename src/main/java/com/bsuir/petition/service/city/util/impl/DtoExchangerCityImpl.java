package com.bsuir.petition.service.city.util.impl;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.service.city.util.DtoExchangerCity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoExchangerCityImpl implements DtoExchangerCity{
    @Override
    public CityDTO getCityDTO(City city) {
        CityDTO cityDTO = new CityDTO();

        cityDTO.setName(city.getName());
        Country country = city.getCountry();
        cityDTO.setCountry(country.getName());

        return cityDTO;
    }

    @Override
    public CityListDTO getCityListDTO(List<City> cities) {
        CityListDTO cityListDTO = new CityListDTO();
        ArrayList<CityDTO> cityDTOs = cityListDTO.getCities();
        Country country;

        for (City city : cities) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(city.getName());
            cityDTO.setId(city.getId());
            country = city.getCountry();
            cityDTO.setCountry(country.getName());
            cityDTOs.add(cityDTO);
        }

        return cityListDTO;
    }
}
