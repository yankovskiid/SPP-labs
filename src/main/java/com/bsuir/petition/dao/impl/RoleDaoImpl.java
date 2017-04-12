package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.dao.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Role role;
        Query query = session.createQuery("from Role where roleName = :roleName");
        query.setParameter("roleName", name);
        role = (Role) query.getSingleResult();
        return role;
    }

    public Role getRoleById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role where id = :roleId").setParameter("roleId", id);
        Role role;
        role = (Role) query.getSingleResult();

        return role;
    }

    @Override
    public List<Role> getRoles() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> roles;
        roles = (List<Role>) session.createQuery("from Role").list();

        return roles;
    }

    public Role createRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);

        return role;
    }

    public Role updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);

        return role;
    }

    public void deleteRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }
}
