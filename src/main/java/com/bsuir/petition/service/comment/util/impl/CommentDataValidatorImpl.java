package com.bsuir.petition.service.comment.util.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.service.comment.util.CommentDataValidator;
import com.bsuir.petition.service.exception.ErrorInputException;

public class CommentDataValidatorImpl implements CommentDataValidator {
    @Override
    public void validate(CommentDTO commentDTO) throws ErrorInputException {
        validateString(commentDTO.getText());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
