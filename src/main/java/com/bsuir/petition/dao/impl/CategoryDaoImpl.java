package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Category getCategory(long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        return category;
    }

    @Override
    public Category getCategory(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category where name = :name");
        query.setParameter("name", name);
        Category category = (Category) query.getSingleResult();
        return category;
    }

    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> categories = (List<Category>) session.createQuery("from Category").list();
        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void deleteCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

    @Override
    public void addCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
    }
}
