package com.bsuir.petition.service.vote.util;

import com.bsuir.petition.bean.dto.vote.ShortVoteDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface VoteDataValidator {
    void validate(ShortVoteDTO shortVoteDTO) throws ErrorInputException;
}
