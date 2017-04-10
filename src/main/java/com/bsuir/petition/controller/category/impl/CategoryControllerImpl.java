package com.bsuir.petition.controller.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.controller.category.CategoryController;
import com.bsuir.petition.service.category.CategoryService;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryControllerImpl implements CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void addCategory(@RequestBody ShortCategoryDTO shortCategoryDTO) throws ServerException, ErrorInputException {
        categoryService.addCategory(shortCategoryDTO);
    }

    @Override
    public void updateCategory(@RequestBody ShortCategoryDTO shortCategoryDTO,
                               @PathVariable long id) throws ErrorInputException, ServerException, CategoryNotFoundException {
        categoryService.updateCategory(shortCategoryDTO, id);
    }

    @Override
    public void deleteCategory(@PathVariable long id) throws ServerException, CategoryNotFoundException {
        categoryService.deleteCategory(id);
    }

    @Override
    public CategoryListDTO getCategories() throws ServerException {
        return categoryService.getCategories();
    }
}
