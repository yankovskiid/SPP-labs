package com.bsuir.petition.controller.category.impl;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.controller.category.CategoryController;
import com.bsuir.petition.service.category.CategoryService;
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
    public void addCategory(@RequestBody ShortCategoryDTO shortCategoryDTO) {
        categoryService.addCategory(shortCategoryDTO);
    }

    @Override
    public void updateCategory(@RequestBody ShortCategoryDTO shortCategoryDTO,
                               @PathVariable long id) {
        categoryService.updateCategory(shortCategoryDTO, id);
    }

    @Override
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
    }

    @Override
    public CategoryListDTO getCategories() {
        return categoryService.getCategories();
    }
}
