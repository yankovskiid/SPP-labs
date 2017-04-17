package com.bsuir.petition.service.category.util;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.entity.Category;

import java.util.Set;

public interface ExchangerCategory {
    Category getCategory(ShortCategoryDTO shortCategoryDTO, long id);
    Category getCategory(ShortCategoryDTO categoryDTO);
    Set<Category> getCategories(CategoryListDTO categoryListDTO);
}
