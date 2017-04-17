package com.bsuir.petition.controller.petition;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface PetitionController {

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/petition/{id}", method = RequestMethod.GET)
    PetitionDTO getPetition(long id) throws ServerException, PetitionNotFoundException;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/petition", method = RequestMethod.POST)
    void addPetition(AddPetitionDTO addPetitionDTO) throws ServerException, ErrorInputException;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/petitions", method = RequestMethod.GET)
    PetitionListDTO getPetitions() throws ServerException;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/petition/{id}", method = RequestMethod.DELETE)
    void deletePetition(long id) throws ServerException, PetitionNotFoundException;

}
