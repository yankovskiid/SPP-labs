package com.bsuir.petition.controller.city;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.controller.city.impl.CityControllerImpl;
import com.bsuir.petition.service.city.CityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CityControllerTest {

    @InjectMocks
    private CityControllerImpl cityControllerImpl;

    @Mock
    private CityService cityService;

    @Test
    public void getCities_shouldReturnCityListDto() throws Exception {
        when(cityService.getCities()).thenReturn(new CityListDTO());

        CityListDTO cities = cityControllerImpl.getCities();

        Assert.assertNotNull(cities);
        verify(cityService, times(1)).getCities();
    }

    @Test
    public void getCities_id_shouldReturnCityListDto() throws Exception {
        when(cityService.getCities(anyLong())).thenReturn(new CityListDTO());

        CityListDTO cities = cityControllerImpl.getCities(0);

        Assert.assertNotNull(cities);
        verify(cityService, times(1)).getCities(anyLong());
    }

    @Test
    public void deleteCity_id() throws Exception {
        cityControllerImpl.deleteCity(0);

        verify(cityService, times(1)).deleteCity(anyLong());
    }

    @Test
    public void updateCity_dto_id() throws Exception {
        cityControllerImpl.updateCity(new CityDTO(), 0);

        verify(cityService, times(1)).updateCity(any(), anyLong());
    }

    @Test
    public void addCity_dto() throws Exception {
        cityControllerImpl.addCity(new CityDTO());

        verify(cityService, times(1)).addCity(any());
    }
}
