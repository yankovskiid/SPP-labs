package com.bsuir.petition.service.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryListDTO getCategories() {
        return null;
    }

    @Override
    public void deleteCategory(long id) {

    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {

    }

    @Override
    public void addCategory(ShortCategoryDTO shortCategoryDTO) {

    }
}
