package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.City;

public interface CityDao {
    City getCityByName(String cityName);
}
