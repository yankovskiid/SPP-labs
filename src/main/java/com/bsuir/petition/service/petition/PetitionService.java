package com.bsuir.petition.service.petition;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;

import java.util.List;

public interface PetitionService {
    PetitionListDTO getPetitions() throws ServerException;
    List<Petition> getPetitionsList();
    void deletePetition(long id) throws PetitionNotFoundException, ServerException;
    void addPetition(AddPetitionDTO addPetitionDTO) throws ServerException, ErrorInputException;
    PetitionDTO getPetition(long id) throws PetitionNotFoundException, ServerException;

}
