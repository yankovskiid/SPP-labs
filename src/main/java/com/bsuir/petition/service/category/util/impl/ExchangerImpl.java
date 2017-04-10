package com.bsuir.petition.service.category.util.impl;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.util.Exchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangerImpl implements Exchanger {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category getCategory(ShortCategoryDTO shortCategoryDTO, long id) {
        Category category = categoryDao.getCategory(id);
        category.setName(shortCategoryDTO.getName());
        return category;
    }

    @Override
    public Category getCategory(ShortCategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(category.getName());
        return category;
    }
}
