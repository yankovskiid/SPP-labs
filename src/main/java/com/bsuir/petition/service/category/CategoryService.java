package com.bsuir.petition.service.category;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.exception.SuchCategoryExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;

public interface CategoryService {
    CategoryListDTO getCategories() throws ServerException;
    void deleteCategory(long id) throws CategoryNotFoundException, ServerException;
    void updateCategory(ShortCategoryDTO shortCategoryDTO, long id) throws CategoryNotFoundException, ErrorInputException, ServerException;
    void addCategory(ShortCategoryDTO shortCategoryDTO) throws ErrorInputException, ServerException, SuchCategoryExistsException;
}
