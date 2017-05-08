package com.bsuir.petition.service.petition.util;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.service.exception.ErrorInputException;

public interface PetitionDataValidator {
    void validate(AddPetitionDTO addPetitionDTO) throws ErrorInputException;

}
