package com.bsuir.petition.service.category.util;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface CategoryDataValidator {
    void validate(ShortCategoryDTO shortCategoryDTO) throws ErrorInputException;
}
