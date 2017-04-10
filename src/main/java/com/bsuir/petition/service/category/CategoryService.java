package com.bsuir.petition.service.category;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;

public interface CategoryService {
    CategoryListDTO getCategories();
    void deleteCategory(long id);
    void updateCategory(ShortCategoryDTO shortCategoryDTO, long id);
    void addCategory(ShortCategoryDTO shortCategoryDTO);
}
