package com.bsuir.petition.bean.dto.country;

import java.util.ArrayList;

public class CountryListDTO {
    private ArrayList<CountryDTO> countries = new ArrayList<>(0);

    public ArrayList<CountryDTO> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<CountryDTO> countries) {
        this.countries = countries;
    }
}
