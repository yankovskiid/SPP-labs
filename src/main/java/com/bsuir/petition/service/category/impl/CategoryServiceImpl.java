package com.bsuir.petition.service.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.CategoryService;
import com.bsuir.petition.service.category.util.DtoExchanger;
import com.bsuir.petition.service.category.util.Exchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    private DtoExchanger dtoExchanger;

    private Exchanger exchanger;

    @Autowired
    public void setDtoExchanger(DtoExchanger dtoExchanger) {
        this.dtoExchanger = dtoExchanger;
    }

    @Autowired
    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryListDTO getCategories() {
        CategoryListDTO categoryListDTO;
        List<Category> categories;
        categories = categoryDao.getCategories();
        categoryListDTO = dtoExchanger.getCategoryListDTO(categories);
        return categoryListDTO;
    }

    @Override
    public void deleteCategory(long id) {
        Category category;
        category = categoryDao.getCategory(id);
        categoryDao.deleteCategory(category);
    }

    @Override
    public void updateCategory(ShortCategoryDTO shortCategoryDTO, long id) {
        Category category;
        category = exchanger.getCategory(shortCategoryDTO, id);
        categoryDao.updateCategory(category);
    }

    @Override
    public void addCategory(ShortCategoryDTO shortCategoryDTO) {
        Category category;
        category = exchanger.getCategory(shortCategoryDTO);
        categoryDao.addCategory(category);
    }
}
