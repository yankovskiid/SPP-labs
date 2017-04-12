package com.bsuir.petition.service.country.util.impl;

import com.bsuir.petition.bean.dto.country.CountryDTO;
import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.service.country.util.DtoExchangerCountry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoExchangerCountryImpl implements DtoExchangerCountry {
    @Override
    public CountryListDTO getCountryListDTO(List<Country> countries) {
        CountryListDTO countryListDTO = new CountryListDTO();
        ArrayList<CountryDTO> countryDTOs = countryListDTO.getCountries();

        for (Country country : countries) {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setName(country.getName());
            countryDTO.setId(country.getId());
            countryDTOs.add(countryDTO);
        }

        return countryListDTO;
    }
}
