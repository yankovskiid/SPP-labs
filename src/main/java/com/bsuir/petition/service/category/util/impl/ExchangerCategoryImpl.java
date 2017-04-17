package com.bsuir.petition.service.category.util.impl;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.util.ExchangerCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExchangerCategoryImpl implements ExchangerCategory {

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
        category.setName(categoryDTO.getName());
        return category;
    }

    @Override
    public Set<Category> getCategories(CategoryListDTO categoryListDTO) {
        Set<Category> result = new HashSet<>(0);
        List<CategoryDTO> categoryDTOs = categoryListDTO.getCategories();
        for (CategoryDTO categoryDTO : categoryDTOs) {
            Category category = categoryDao.getCategory(categoryDTO.getName());
            result.add(category);
        }
        return result;
    }
}
