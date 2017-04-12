package com.bsuir.petition.service.category.util.impl;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.service.category.util.DtoExchangerCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoExchangerCategoryImpl implements DtoExchangerCategory {
    @Override
    public CategoryListDTO getCategoryListDTO(List<Category> categories) {
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        ArrayList<CategoryDTO> categoryDTOs = categoryListDTO.getCategories();

        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(category.getName());
            categoryDTO.setId(category.getId());
            categoryDTOs.add(categoryDTO);
        }

        return categoryListDTO;
    }
}
