package com.bsuir.petition.controller.category;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CategoryController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    void addCategory(ShortCategoryDTO shortCategoryDTO);

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/category/{id}")
    void updateCategory(ShortCategoryDTO shortCategoryDTO, long id);

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    void deleteCategory(long id);

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    CategoryListDTO getCategories();
}
