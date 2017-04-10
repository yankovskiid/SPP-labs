package com.bsuir.petition.service.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.CategoryService;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.util.DtoExchangerCategory;
import com.bsuir.petition.service.category.util.ExchangerCategory;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    private DtoExchangerCategory dtoExchangerCategory;

    private ExchangerCategory exchangerCategory;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setDtoExchangerCategory(DtoExchangerCategory dtoExchangerCategory) {
        this.dtoExchangerCategory = dtoExchangerCategory;
    }

    @Autowired
    public void setExchangerCategory(ExchangerCategory exchangerCategory) {
        this.exchangerCategory = exchangerCategory;
    }

    @Override
    public CategoryListDTO getCategories() throws ServerException {
        CategoryListDTO categoryListDTO;
        List<Category> categories;
        categories = categoryDao.getCategories();
        categoryListDTO = dtoExchangerCategory.getCategoryListDTO(categories);
        return categoryListDTO;
    }

    @Override
    public void deleteCategory(long id) throws CategoryNotFoundException, ServerException {
        Category category;
        category = categoryDao.getCategory(id);
        categoryDao.deleteCategory(category);
    }

    @Override
    public void updateCategory(ShortCategoryDTO shortCategoryDTO, long id) throws CategoryNotFoundException, ErrorInputException, ServerException {
        Category category;
        category = exchangerCategory.getCategory(shortCategoryDTO, id);
        categoryDao.updateCategory(category);
    }

    @Override
    public void addCategory(ShortCategoryDTO shortCategoryDTO) throws ErrorInputException, ServerException {
        Category category;
        category = exchangerCategory.getCategory(shortCategoryDTO);
        categoryDao.addCategory(category);
    }
}
