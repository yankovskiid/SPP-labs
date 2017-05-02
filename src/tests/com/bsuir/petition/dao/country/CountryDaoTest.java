package com.bsuir.petition.dao.country;

import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.impl.CountryDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CountryDaoTest {

    @InjectMocks
    private CountryDaoImpl countryDaoImpl;

    @Mock
    private SessionFactory sessionFactory;

    private Session session;
    private Query query;

    @Before
    public void setUp() {
        session = mock(Session.class);
        query = mock(Query.class);
    }

    @Test
    public void getCountry_countryId() {
        givenSessionFactoryReturnsCurrentSession();
        givenSessionGetCountryByIdReturnsCountry();

        Country actual = countryDaoImpl.getCountry(0);

        Assert.assertNotNull(actual);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionGetCountryByIdWasCalled();
    }

    @Test(expected = NullPointerException.class)
    public void getCountry_id_shouldThrowNullPointer() throws Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(null);

        Country country = countryDaoImpl.getCountry(0);
    }

    @Test
    public void getCountry_countryName() {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsCountry();

        Country country = countryDaoImpl.getCountry("");

        Assert.assertNotNull(country);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetSingleResultWasCalled();
    }

    @Test
    public void getCountries_shouldReturnListOfCountry() {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsListOfCountry();

        List<Country> countries = countryDaoImpl.getCountries();

        Assert.assertNotNull(countries);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQueryListWasCalled();
    }

    @Test
    public void updateCountry() {
        givenSessionFactoryReturnsCurrentSession();

        countryDaoImpl.updateCountry(new Country());

        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionSaveOrUpdateWasCalled();
    }

    @Test
    public void deleteCountry() {
        givenSessionFactoryReturnsCurrentSession();

        countryDaoImpl.deleteCountry(new Country());

        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionDeleteWasCalled();
    }

    @Test
    public void addCountry() {
        givenSessionFactoryReturnsCurrentSession();

        countryDaoImpl.addCountry(new Country());

        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionSaveWasCalled();
    }

    /*getCountry(id)*/
    private void givenSessionGetCountryByIdReturnsCountry() {
        when(session.get(eq(Country.class), anyLong())).thenReturn(new Country());
    }

    private void givenSessionFactoryReturnsCurrentSession() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    private void verifyThatSessionGetCountryByIdWasCalled() {
        verify(session, times(1)).get(eq(Country.class), anyLong());
    }

    private void verifyThatGetCurrentSessionWasCalled() {
        verify(sessionFactory, times(1)).getCurrentSession();
    }


    /*getCountry(name)*/
    private void giverSessionReturnsQuery() {
        when(session.createQuery(anyString())).thenReturn(query);
    }

    private void givenQueryReturnsCountry() {
        when(query.getSingleResult()).thenReturn(new Country());
    }

    private void verifyThatSessionCreateQueryWasCalled() {
        verify(session, times(1)).createQuery(anyString());
    }

    private void verifyThatQuerySetParameterWasCalled() {
        verify(query, times(1)).setParameter(anyString(), anyString());
    }

    private void verifyThatQueryGetSingleResultWasCalled() {
        verify(query, times(1)).getSingleResult();
    }

    /*getCountries()*/
    private void givenQueryReturnsListOfCountry() {
        when(query.list()).thenReturn(Collections.emptyList());
    }

    private void verifyThatQueryListWasCalled() {
        verify(query, times(1)).list();
    }

    /*updateCountry()*/
    private void verifyThatSessionSaveOrUpdateWasCalled() {
        verify(session, times(1)).saveOrUpdate(any());
    }

    /*addCountry()*/
    private void verifyThatSessionSaveWasCalled() {
        verify(session, times(1)).save(any());
    }

    /*deleteCountry()*/
    private void verifyThatSessionDeleteWasCalled() {
        verify(session, times(1)).delete(any());
    }
}
