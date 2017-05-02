package com.bsuir.petition.service.country;

import com.bsuir.petition.bean.dto.country.CountryListDTO;
import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CountryDao;
import com.bsuir.petition.service.country.exception.CountryNotFoundException;
import com.bsuir.petition.service.country.impl.CountryServiceImpl;
import com.bsuir.petition.service.country.util.CountryDataValidator;
import com.bsuir.petition.service.country.util.DtoExchangerCountry;
import com.bsuir.petition.service.country.util.ExchangerCountry;
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
public class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl countryServiceImpl;

    @Mock
    private CountryDataValidator countryDataValidator;

    @Mock
    private CountryDao countryDao;

    @Mock
    private DtoExchangerCountry dtoExchangerCountry;

    @Mock
    private ExchangerCountry exchangerCountry;

    /*CountryService.getCountries()*/
    @Test
    public void getCountries_shouldReturnListOfCountries() throws Exception {
        givenCountryDaoReturnsListOfCountries();
        givenDtoExchangerCountryReturnsListOfCountryDto();

        CountryListDTO actualDto = countryServiceImpl.getCountries();

        Assert.assertNotNull(actualDto);
        verifyThatCountryDaoWasCalled();
        verifyThatDtoExchangerCountry();
    }

    @Test(expected = ServerException.class)
    public void getCountries_hibernateErrorInCountryDao_shouldThrowException() throws Exception {
        givenCountryDaoThrowsHibernateException();
        givenDtoExchangerCountryReturnsListOfCountryDto();

        countryServiceImpl.getCountries();
    }

    @Test(expected = ServerException.class)
    public void getCountry_exceptionInExchanger_shouldThrowException() throws Exception {
        givenCountryDaoReturnsListOfCountries();
        givenDtoExchangerCountryThrowsException();

        countryServiceImpl.getCountries();
    }


    /*CountryService.deleteCountry(id)*/
    @Test
    public void deleteCountry_countryId() throws Exception {
        givenCountryDaoReturnsCountry();

        countryServiceImpl.deleteCountry(0);

        verifyThatCountryDaoGetCountryWasCalled();
    }

    @Test(expected = CountryNotFoundException.class)
    public void deleteCountry_countryId_shouldThrowCountryNotFound() throws Exception {
        givenCountryDaoReturnsNull();

        countryServiceImpl.deleteCountry(0);
    }

    /*CountryService.updateCountry(dto, id)*/
    @Test
    public void updateCategory_shortDto_categoryId() throws Exception {
        givenExchangerCountryReturnsCountry();

        countryServiceImpl.updateCountry(any(), 0);

        verifyThatCountryDataValidatorWasCalled();
        verifyThatExchangerCountryWasCalled();
        verifyThatCountryDaoUpdateCountryWasCalled();
    }

    /*CountryService.addCountry(dto)*/
    @Test
    public void addCategory_shortDto() throws Exception {
        givenExchangerCountryReturnsCountry();

        countryServiceImpl.addCountry(any());

        verifyThatCountryDataValidatorWasCalled();
        verifyThatExchangerCountryWasCalledByShort();
        verifyThatCountryDaoAddCountryWasCalled();
    }


    /*CountryService.getCountries()*/
    private void givenCountryDaoReturnsListOfCountries() {
        when(countryDao.getCountries()).thenReturn(Collections.emptyList());
    }

    private void givenDtoExchangerCountryReturnsListOfCountryDto() {
        when(dtoExchangerCountry.getCountryListDTO(any())).thenReturn(new CountryListDTO());
    }

    private void givenCountryDaoThrowsHibernateException() {
        when(countryDao.getCountries()).thenThrow(HibernateException.class);
    }

    private void givenDtoExchangerCountryThrowsException(){
        when(dtoExchangerCountry.getCountryListDTO(any())).thenThrow(new RuntimeException());
    }

    private void verifyThatCountryDaoWasCalled() {
        verify(countryDao, times(1)).getCountries();
    }

    private void verifyThatDtoExchangerCountry() {
        verify(dtoExchangerCountry, times(1)).getCountryListDTO(any());
    }

    /*CountryService.deleteCountry(id)*/
    private void givenCountryDaoReturnsCountry() {
        when(countryDao.getCountry(anyLong())).thenReturn(new Country());
    }

    private void givenCountryDaoReturnsNull() {
        when(countryDao.getCountry(anyLong())).thenReturn(null);
    }

    private void verifyThatCountryDaoGetCountryWasCalled() {
        verify(countryDao, times(1)).getCountry(anyLong());
    }

    /*CountryService.updateCountry(dto, id)*/
    private void givenExchangerCountryReturnsCountry() {
        when(exchangerCountry.getCountry(any())).thenReturn(new Country());
    }

    private void verifyThatCountryDataValidatorWasCalled() throws ErrorInputException {
        verify(countryDataValidator, times(1)).validate(any());
    }

    private void verifyThatExchangerCountryWasCalled() {
        verify(exchangerCountry, times(1)).getCountry(any(), anyLong());
    }

    private void verifyThatCountryDaoUpdateCountryWasCalled() {
        verify(countryDao, times(1)).updateCountry(any());
    }

    /*CountryService.addCountry(dto)*/
    private void verifyThatCountryDaoAddCountryWasCalled() {
        verify(countryDao, times(1)).addCountry(any());
    }

    private void verifyThatExchangerCountryWasCalledByShort() {
        verify(exchangerCountry, times(1)).getCountry(any());
    }

}
