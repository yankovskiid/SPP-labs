package com.bsuir.petition.service.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.CategoryService;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.exception.SuchCategoryExistsException;
import com.bsuir.petition.service.category.util.CategoryDataValidator;
import com.bsuir.petition.service.category.util.DtoExchangerCategory;
import com.bsuir.petition.service.category.util.ExchangerCategory;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDataValidator categoryDataValidator;

    private CategoryDao categoryDao;

    private DtoExchangerCategory dtoExchangerCategory;

    private ExchangerCategory exchangerCategory;

    @Autowired
    public void setCategoryDataValidator(CategoryDataValidator categoryDataValidator) {
        this.categoryDataValidator = categoryDataValidator;
    }

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
        try {
            categories = categoryDao.getCategories();
            categoryListDTO = dtoExchangerCategory.getCategoryListDTO(categories);
        } catch (Exception exception) {
            throw new ServerException("Server exception!", exception);
        }
        return categoryListDTO;
    }

    @Override
    public void deleteCategory(long id) throws CategoryNotFoundException, ServerException {
        Category category;
        category = categoryDao.getCategory(id);

        if (category == null) {
            throw new CategoryNotFoundException("Category not found!");
        }

        try {
            categoryDao.deleteCategory(category);
        } catch (HibernateException exception) {
            throw new ServerException("Such category does", exception);
        }
    }

    @Override
    public void updateCategory(ShortCategoryDTO shortCategoryDTO, long id) throws CategoryNotFoundException, ErrorInputException, ServerException {

        categoryDataValidator.validate(shortCategoryDTO);

        Category category;
        category = exchangerCategory.getCategory(shortCategoryDTO, id);
        try {
            categoryDao.updateCategory(category);
        } catch (HibernateException exception) {
            throw new CategoryNotFoundException("No such category!", exception);
        }
    }

    @Override
    public void addCategory(ShortCategoryDTO shortCategoryDTO) throws ErrorInputException, ServerException, SuchCategoryExistsException {

        categoryDataValidator.validate(shortCategoryDTO);

        Category category;
        category = exchangerCategory.getCategory(shortCategoryDTO);
        try {
            categoryDao.addCategory(category);
        } catch (HibernateException exception) {
            throw new SuchCategoryExistsException("Server exception!", exception);
        }
    }
}
