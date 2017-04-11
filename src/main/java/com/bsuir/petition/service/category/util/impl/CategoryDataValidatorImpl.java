package com.bsuir.petition.service.category.util.impl;

import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.service.category.util.CategoryDataValidator;
import com.bsuir.petition.service.exception.ErrorInputException;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataValidatorImpl implements CategoryDataValidator {
    @Override
    public void validate(ShortCategoryDTO shortCategoryDTO) throws ErrorInputException {
        validateString(shortCategoryDTO.getName());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
