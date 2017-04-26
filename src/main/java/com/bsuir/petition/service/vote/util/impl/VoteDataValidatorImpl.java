package com.bsuir.petition.service.vote.util.impl;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.vote.util.VoteDataValidator;
import org.springframework.stereotype.Component;

@Component
public class VoteDataValidatorImpl implements VoteDataValidator {

    @Override
    public void validate(ShortVoteDTO shortVoteDTO) throws ErrorInputException {
        validateString(shortVoteDTO.getReason());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }
}
