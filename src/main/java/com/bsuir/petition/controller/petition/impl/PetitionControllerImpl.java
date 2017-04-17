package com.bsuir.petition.controller.petition.impl;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.controller.petition.PetitionController;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.PetitionService;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetitionControllerImpl implements PetitionController {

    private PetitionService petitionService;

    @Autowired
    public void setPetitionService(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @Override
    public PetitionDTO getPetition(@PathVariable long id) throws ServerException, PetitionNotFoundException {
        PetitionDTO petitionDTO;
        petitionDTO = petitionService.getPetition(id);
        return petitionDTO;
    }

    @Override
    public void addPetition(@RequestBody AddPetitionDTO addPetitionDTO) throws ServerException, ErrorInputException {
        petitionService.addPetition(addPetitionDTO);
    }

    @Override
    public PetitionListDTO getPetitions() throws ServerException {
        PetitionListDTO petitionListDTO;
        petitionListDTO = petitionService.getPetitions();
        return petitionListDTO;
    }

    @Override
    public void deletePetition(@PathVariable long id) throws ServerException, PetitionNotFoundException {
        petitionService.deletePetition(id);
    }
}
