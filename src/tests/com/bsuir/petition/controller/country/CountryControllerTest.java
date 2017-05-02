package com.bsuir.petition.controller.country;

import com.bsuir.petition.bean.dto.country.CountryDTO;
import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.controller.country.impl.CountryControllerImpl;
import com.bsuir.petition.service.country.CountryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CountryControllerTest {

    @InjectMocks
    private CountryControllerImpl countryControllerImpl;

    @Mock
    private CountryService countryService;

    @Test
    public void getCountries_shouldReturnCountryListDto() throws Exception {
        when(countryService.getCountries()).thenReturn(new CountryListDTO());

        CountryListDTO countries = countryControllerImpl.getCountries();

        Assert.assertNotNull(countries);
        verify(countryService, times(1)).getCountries();
    }

    @Test
    public void deleteCountry_id() throws Exception {
        countryControllerImpl.deleteCountry(0);

        verify(countryService, times(1)).deleteCountry(anyLong());
    }

    @Test
    public void updateCountry_dto_id() throws Exception {
        countryControllerImpl.updateCountry(new CountryDTO(), 0);

        verify(countryService, times(1)).updateCountry(any(), anyLong());
    }

    @Test
    public void addCountry_dto() throws Exception {
        countryControllerImpl.addCountry(new CountryDTO());

        verify(countryService, times(1)).addCountry(any());
    }
}
