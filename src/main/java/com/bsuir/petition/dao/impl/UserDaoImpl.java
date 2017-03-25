package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getUserById(long id) {
        User user = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            user = session.get(User.class, id);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return user;
    }

    public void updateUserById(long id, User user) {

    }

    public void deleteUserById(long id) {

    }

    public void addUser(User user) {

    }
}
