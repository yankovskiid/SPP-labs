package com.bsuir.petition.dao.city;

import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.dao.impl.CityDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CityDaoTest {

    @InjectMocks
    private CityDaoImpl cityDaoImpl;

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
    public void getCity_name_shouldReturnCity() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsCity();

        City city = cityDaoImpl.getCityByName("");

        Assert.assertNotNull(city);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetSingleResultWasCalled();
    }

    @Test(expected = NullPointerException.class)
    public void getCities_countryId_shouldThrowNullPointer() throws Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(null);

        List<City> cities = cityDaoImpl.getCities(0);
    }

    @Test
    public void getCity_id_shouldReturnCity() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsCity();

        City city = cityDaoImpl.getCityById(0);

        Assert.assertNotNull(city);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetSingleResultWasCalled();
    }

    @Test
    public void getCities_countryId_shouldReturnListOfCity() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsResultListOfCity();

        List<City> cities = cityDaoImpl.getCities(0);

        Assert.assertNotNull(cities);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetResultListWasCalled();
    }

    @Test
    public void getCities_shouldReturnListOfCity() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsListOfCity();

        List<City> cities = cityDaoImpl.getCities();

        Assert.assertNotNull(cities);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQueryListWasCalled();
    }

    @Test
    public void addCity_city() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        cityDaoImpl.addCity(new City());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).save(any());
    }

    @Test
    public void updateCity_city() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        cityDaoImpl.updateCity(new City());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).saveOrUpdate(any());
    }

    @Test
    public void deleteCity_city() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        cityDaoImpl.deleteCity(new City());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).delete(any());
    }

    private void givenQueryReturnsResultListOfCity() {
        when(query.getResultList()).thenReturn(Collections.emptyList());
    }

    private void givenQueryReturnsListOfCity() {
        when(query.list()).thenReturn(Collections.emptyList());
    }

    private void givenSessionFactoryReturnsCurrentSession() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    private void givenQueryReturnsCity() {
        when(query.getSingleResult()).thenReturn(new City());
    }

    private void giverSessionReturnsQuery() {
        when(session.createQuery(anyString())).thenReturn(query);
    }

    private void verifyThatGetCurrentSessionWasCalled() {
        verify(sessionFactory, times(1)).getCurrentSession();
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

    private void verifyThatQueryListWasCalled() {
        verify(query, times(1)).list();
    }

    private void verifyThatQueryGetResultListWasCalled() {
        verify(query, times(1)).getResultList();
    }

}
