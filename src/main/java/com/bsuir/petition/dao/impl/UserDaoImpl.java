package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            user = session.load(User.class, id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
            CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery( User.class );
            Root<User> personRoot = criteria.from( User.class );
            criteria.select( personRoot );
            criteria.where( builder.equal( personRoot.get("email"), userEmail));
            user = session.createQuery(criteria).getSingleResult();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUserById(long id, User user) {

    }

    @Override
    public void deleteUserById(long id) {

    }
}
