package com.bsuir.petition.service.petition.impl;

import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.PetitionService;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import com.bsuir.petition.service.petition.util.PetitionDtoExchanger;
import com.bsuir.petition.service.petition.util.PetitionExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionServiceImpl implements PetitionService {

    private PetitionDao petitionDao;

    private PetitionDtoExchanger petitionDtoExchanger;

    private PetitionExchanger petitionExchanger;

    @Autowired
    public void setPetitionDao(PetitionDao petitionDao) {
        this.petitionDao = petitionDao;
    }

    @Autowired
    public void setPetitionDtoExchanger(PetitionDtoExchanger petitionDtoExchanger) {
        this.petitionDtoExchanger = petitionDtoExchanger;
    }

    @Autowired
    public void setPetitionExchanger(PetitionExchanger petitionExchanger) {
        this.petitionExchanger = petitionExchanger;
    }

    @Override
    public PetitionListDTO getPetitions() throws ServerException {
        PetitionListDTO petitionListDTO;
        List<Petition> petitions = petitionDao.getPetitions();
        petitionListDTO = petitionDtoExchanger.getPetitionListDTO(petitions);
        return petitionListDTO;
    }

    @Override
    public void deletePetition(long id) throws PetitionNotFoundException, ServerException {
        Petition petition;
        petition = petitionDao.getPetition(id);
        petitionDao.deletePetition(petition);
    }

    @Override
    public void addPetition(PetitionDTO petitionDTO) throws ServerException, ErrorInputException {
        Petition petition;
        petition = petitionExchanger.getPetition(petitionDTO);
        petitionDao.addPetition(petition);
    }

    @Override
    public PetitionDTO getPetition(long id) throws PetitionNotFoundException, ServerException {
        PetitionDTO petitionDTO;
        Petition petition;
        petition = petitionDao.getPetition(id);
        petitionDTO = petitionDtoExchanger.getPetitionDTO(petition);
        return petitionDTO;
    }
}
