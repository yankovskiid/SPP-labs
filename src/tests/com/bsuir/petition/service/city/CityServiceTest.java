package com.bsuir.petition.service.city;

import com.bsuir.petition.bean.dto.city.CityDTO;
import com.bsuir.petition.bean.dto.city.CityListDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.city.exception.CityNotFoundException;
import com.bsuir.petition.service.city.impl.CityServiceImpl;
import com.bsuir.petition.service.city.util.CityDataValidator;
import com.bsuir.petition.service.city.util.DtoExchangerCity;
import com.bsuir.petition.service.city.util.ExchangerCity;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @InjectMocks
    private CityServiceImpl cityServiceImpl;

    @Mock
    private CityDataValidator cityDataValidator;

    @Mock
    private CityDao cityDao;

    @Mock
    private DtoExchangerCity dtoExchangerCity;

    @Mock
    private ExchangerCity exchangerCity;

    @Mock
    private CountryDao countryDao;

    /*CityService.getCities()*/
    @Test
    public void getCities_shouldReturnListOfCities() throws Exception {
        givenCityDaoReturnsListOfCities();
        givenDtoExchangerCityReturnsListOfCityDto();

        CityListDTO actualDto = cityServiceImpl.getCities();

        Assert.assertNotNull(actualDto);
        verifyThatCityDaoWasCalled();
        verifyThatDtoExchangerCity();
    }

    @Test(expected = ServerException.class)
    public void getCities_hibernateErrorInCityDao_shouldThrowException() throws Exception {
        givenCityDaoThrowsHibernateException();
        givenDtoExchangerCityReturnsListOfCityDto();

        cityServiceImpl.getCities();
    }

    @Test(expected = ServerException.class)
    public void getCities_exceptionInExchanger_shouldThrowException() throws Exception {
        givenCityDaoReturnsListOfCities();
        givenDtoExchangerCityThrowsException();

        cityServiceImpl.getCities();
    }


    /*CityService.getCities(countryId)*/
    @Test
    public void getCities_countryId_shouldReturnListOfCities() throws Exception {
        givenCountryDaoReturnsCountryById();
        givenCityDaoReturnsListOfCitiesByCountryId();
        givenDtoExchangerCityReturnsListOfCityDto();

        CityListDTO actualDto = cityServiceImpl.getCities(0);

        Assert.assertNotNull(actualDto);
        verifyThatCountryDaoWasCalled();
        verifyThatCityDaoWasCalledByCountryId();
        verifyThatDtoExchangerCity();
    }

    @Test(expected = CountryNotFoundException.class)
    public void getCities_countryId_shouldThrowException() throws Exception {
        givenCountryDaoReturnsNull();

        cityServiceImpl.getCities(0);
    }

    @Test(expected = ServerException.class)
    public void getCities_countryId_hibernateErrorInCityDao_shouldThrowException() throws Exception {
        givenCountryDaoReturnsCountryById();
        givenCityDaoByCountryIdThrowsHibernateException();
        givenDtoExchangerCityReturnsListOfCityDto();

        cityServiceImpl.getCities(0);
    }



    /*CityService.deleteCity(id)*/
    @Test
    public void deleteCity_cityId() throws Exception {
        givenCityDaoReturnsCityByCityId();

        cityServiceImpl.deleteCity(0);

        verifyThatCityDaoWasCalledByCityId();
        verifyThatDeleteInCityDaoWasCalled();
    }

    @Test(expected = CityNotFoundException.class)
    public void deleteCity_cityId_shouldThrowException() throws Exception {
        givenCityDaoByCityIdThrowsException();

        cityServiceImpl.deleteCity(0);
    }

    @Test(expected = ServerException.class)
    public void deleteCity_cityId_hibernateException_shouldThrowException() throws Exception {
        givenCityDaoByCityIdThrowsHibernateException();

        cityServiceImpl.deleteCity(0);
    }


    /*CityService.updateCity(cityDto, cityId)*/
    @Test
    public void updateCity_cityDto_cityId() throws Exception {
        givenExchangerCityByCityDtoAndCityIdReturnsCity();

        cityServiceImpl.updateCity(any(), 0);

        verifyThatCityDataValidatorWasCalledByCityDto();
        verifyThatExchangerCityWasCalledByCityDtoAndCityId();
        verifyThatCityDaoUpdateCityWasCalled();
    }


    /*CityService.adCity(cityDto)*/
    @Test
    public void addCity_cityDto() throws Exception {
        givenExchangerCityByCityDtoAndCityIdReturnsCity();

        cityServiceImpl.addCity(any());

        verifyThatCityDataValidatorWasCalledByCityDto();
        verifyThatExchangerCityWasCalledByCityDto();
        verifyThatCityDaoAddCityWasCalled();
    }

    /*CityService.getCities()*/
    private void givenDtoExchangerCityReturnsListOfCityDto() {
        when(dtoExchangerCity.getCityListDTO(any())).thenReturn(new CityListDTO());
    }

    private void givenCityDaoThrowsHibernateException() {
        when(cityDao.getCities()).thenThrow(HibernateException.class);
    }

    private void givenDtoExchangerCityThrowsException() {
        when(dtoExchangerCity.getCityListDTO(any())).thenThrow(new RuntimeException());
    }

    private void givenCityDaoReturnsListOfCities() {
        when(cityDao.getCities()).thenReturn(Collections.emptyList());
    }

    private void verifyThatCityDaoWasCalled() {
        verify(cityDao, times(1)).getCities();
    }

    private void verifyThatDtoExchangerCity() {
        verify(dtoExchangerCity, times(1)).getCityListDTO(any());
    }


    /*CityService.getCities(countryId)*/
    private void givenCountryDaoReturnsCountryById() {
        when(countryDao.getCountry(anyLong())).thenReturn(new Country());
    }

    private void givenCityDaoReturnsListOfCitiesByCountryId() {
        when(cityDao.getCities(anyLong())).thenReturn(Collections.emptyList());
    }

    private void givenCountryDaoReturnsNull() {
        when(countryDao.getCountry(anyLong())).thenReturn(null);
    }

    private void givenCityDaoByCountryIdThrowsHibernateException() {
        when(cityDao.getCities(anyLong())).thenThrow(HibernateException.class);
    }

    private void verifyThatCountryDaoWasCalled() {
        verify(countryDao, times(1)).getCountry(anyLong());
    }

    private void verifyThatCityDaoWasCalledByCountryId() {
        verify(cityDao, times(1)).getCities(anyLong());
    }


    /*CityService.deleteCity(id)*/
    private void givenCityDaoReturnsCityByCityId() {
        when(cityDao.getCityById(anyLong())).thenReturn(new City());
    }

    private void givenCityDaoByCityIdThrowsException() {
        when(cityDao.getCityById(anyLong())).thenThrow(CityNotFoundException.class);
    }

    private void givenCityDaoByCityIdThrowsHibernateException() {
        when(cityDao.getCityById(anyLong())).thenThrow(HibernateException.class);
    }

    private void verifyThatCityDaoWasCalledByCityId() {
        verify(cityDao, times(1)).getCityById(anyLong());
    }

    private void verifyThatDeleteInCityDaoWasCalled() {
        verify(cityDao, times(1)).deleteCity(any());
    }


    /*CityService.updateCity(cityDto, cityId)*/
    private void givenExchangerCityByCityDtoAndCityIdReturnsCity() {
        when(exchangerCity.getCity(any(), anyLong())).thenReturn(new City());
    }

    private void verifyThatCityDataValidatorWasCalledByCityDto() throws ErrorInputException {
        verify(cityDataValidator, times(1)).validate(any());
    }

    private void verifyThatExchangerCityWasCalledByCityDto() {
        verify(exchangerCity, times(1)).getCity(any());
    }

    private void verifyThatCityDaoUpdateCityWasCalled() {
        verify(cityDao, times(1)).updateCity(any());
    }

    private void verifyThatExchangerCityWasCalledByCityDtoAndCityId() {
        verify(exchangerCity, times(1)).getCity(any(), anyLong());
    }


    /*CityService.addCity(cityDto)*/
    private void verifyThatCityDaoAddCityWasCalled() {
        verify(cityDao, times(1)).addCity(any());
    }

}
