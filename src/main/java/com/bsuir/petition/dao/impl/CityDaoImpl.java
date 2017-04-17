package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.dao.CityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
