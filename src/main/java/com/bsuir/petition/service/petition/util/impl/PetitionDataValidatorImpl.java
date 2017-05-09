package com.bsuir.petition.service.petition.util.impl;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.petition.util.PetitionDataValidator;
import org.springframework.stereotype.Component;

@Component
public class PetitionDataValidatorImpl implements PetitionDataValidator {

    @Override
    public void validate(AddPetitionDTO addPetitionDTO) throws ErrorInputException {
        validateString(addPetitionDTO.getName());
        validateString(addPetitionDTO.getDescription());
        validateNumber(addPetitionDTO.getNumberNecessaryVotes());
    }

    private void validateString(String data) throws ErrorInputException {
        if (data == null || data.isEmpty()) {
            throw new ErrorInputException("Wrong data input, not all data!");
        }
    }

    private void validateNumber(long data) throws ErrorInputException {
        if(data <= 0) {
           throw new  ErrorInputException("Wrong data input, not all data!");
        }
    }
}
