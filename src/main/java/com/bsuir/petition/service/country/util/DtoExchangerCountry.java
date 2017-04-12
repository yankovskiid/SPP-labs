package com.bsuir.petition.service.country.util;

import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.entity.Country;

import java.util.List;

public interface DtoExchangerCountry {
    CountryListDTO getCountryListDTO(List<Country> countries);
}
