package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.dao.CityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public City getCityByName(String cityName) {
        Session session = sessionFactory.getCurrentSession();
        City city;
        Query query = session.createQuery("from City where name = :cityName");
        query.setParameter("cityName", cityName);
        city = (City)query.getSingleResult();
        return city;
    }

    @Override
    public City getCityById(long id) {
        Session session = sessionFactory.getCurrentSession();
        City city;
        Query query = session.createQuery("from City where id = :cityId");
        query.setParameter("cityId", id);
        city = (City)query.getSingleResult();
        return city;
    }

    @Override
    public void addCity(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.save(city);
    }

    @Override
    public void updateCity(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(city);
    }

    @Override
    public void deleteCity(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(city);
    }

    @Override
    public List<City> getCities() {
        Session session = sessionFactory.getCurrentSession();
        List<City> cities = (List<City>) session.createQuery("from City ").list();
        return cities;
    }

    @Override
    public List<City> getCities(long countryId) {
        Session session = sessionFactory.getCurrentSession();
        List<City> cities;
        Query query = session.createQuery("from City where country_id = :idCountry");
        query.setParameter("idCountry", countryId);
        cities = (List<City>)query.getResultList();
        return cities;
    }
}
