package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Country;
import com.bsuir.petition.dao.CountryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country getCountry(long id) {
        Session session = sessionFactory.getCurrentSession();
        Country country = session.get(Country.class, id);
        return country;
    }

    @Override
    public Country getCountry(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Country where name = :name");
        query.setParameter("name", name);
        Country country = (Country) query.getSingleResult();
        return country;
    }

    @Override
    public List<Country> getCountries() {
        Session session = sessionFactory.getCurrentSession();
        List<Country> countries = (List<Country>) session.createQuery("from Country").list();
        return countries;
    }

    @Override
    public void updateCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(country);
    }

    @Override
    public void deleteCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(country);
    }

    @Override
    public void addCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.save(country);
    }
}
