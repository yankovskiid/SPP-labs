package com.bsuir.petition.dao.impl;

import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryImpl implements CategoryDao {
    @Override
    public Category getCategory(long id) {
        return null;
    }

    @Override
    public Category getCategory(String name) {
        return null;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(long id) {

    }
}
