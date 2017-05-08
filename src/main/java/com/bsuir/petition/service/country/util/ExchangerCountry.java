package com.bsuir.petition.service.country.util;

import com.bsuir.petition.bean.dto.country.CountryDTO;
import com.bsuir.petition.bean.dto.country.ShortCountryDTO;
import com.bsuir.petition.bean.entity.Country;

public interface ExchangerCountry {
    Country getCountry(CountryDTO countryDTO, long id);
    Country getCountry(ShortCountryDTO countryDTO);
}
