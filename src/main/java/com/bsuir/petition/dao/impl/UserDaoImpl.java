package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    private final static long USER_ROLE = 1;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(long id) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        user = session.get(User.class, id);
        return user;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email = :inputEmail");
        query.setParameter("inputEmail", userEmail);
        user = (User)query.getSingleResult();
        return user;
    }

    @Override
    public UserInformation getUserInformationById(long id) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        user = session.load(User.class, id);
        return user.getUserInformation();
    }

    @Override
    public void updateUserInformation(UserInformation userInformation) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(userInformation);
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.load(Role.class, USER_ROLE);
        Set<Role> roles = new HashSet<Role>(0);
        roles.add(role);
        user.setRoles(roles);
        session.save(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users;
        Session session = sessionFactory.getCurrentSession();
        users = (List<User>) session.createQuery("from User").list();
        return users;
    }
}
