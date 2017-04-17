package com.bsuir.petition.bean.dto.city;

import java.util.ArrayList;

public class CityListDTO {
    private ArrayList<CityDTO> cities = new ArrayList<>(0);

    public ArrayList<CityDTO> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CityDTO> cities) {
        this.cities = cities;
    }
}
