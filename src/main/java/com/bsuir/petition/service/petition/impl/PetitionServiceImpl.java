package com.bsuir.petition.service.petition.impl;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.PetitionService;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import com.bsuir.petition.service.petition.util.PetitionDataValidator;
import com.bsuir.petition.service.petition.util.PetitionDtoExchanger;
import com.bsuir.petition.service.petition.util.PetitionExchanger;
import com.sun.security.ntlm.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetitionServiceImpl implements PetitionService {

    private UserDao userDao;

    private PetitionDao petitionDao;

    private PetitionDtoExchanger petitionDtoExchanger;

    private PetitionExchanger petitionExchanger;
    private PetitionDataValidator petitionDataValidator;

    @Autowired
    public void setPetitionDataValidator(PetitionDataValidator petitionDataValidator) {
        this.petitionDataValidator = petitionDataValidator;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

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
    public List<Petition> getPetitionsList() {
        List<Petition> petitions = petitionDao.getPetitions();
        return petitions;
    }

    @Override
    public PetitionListDTO getCompletedPetitions() throws ServerException {
        PetitionListDTO petitionDTOList;
        List<Petition> petitions = petitionDao.getCompletedPetitions();
        petitionDTOList = petitionDtoExchanger.getPetitionListDTO(petitions);
        return petitionDTOList;
    }

    @Override
    public void deletePetition(long id) throws PetitionNotFoundException, ServerException {
        Petition petition;
        petition = petitionDao.getPetition(id);
        if (petition == null) {
            throw new PetitionNotFoundException("No such petition");
        }
        petitionDao.deletePetition(petition);
    }

    @Override
    public void addPetition(AddPetitionDTO addPetitionDTO) throws ServerException, ErrorInputException {
        petitionDataValidator.validate(addPetitionDTO);

        Petition petition;

        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        User user;
        user = userDao.getUserById((Long) tokenAuthentication.getDetails());

        petition = petitionExchanger.getPetition(addPetitionDTO, user);
        petitionDao.addPetition(petition);
    }

    @Override
    public PetitionDTO getPetition(long id) throws PetitionNotFoundException, ServerException {
        PetitionDTO petitionDTO;
        Petition petition;
        petition = petitionDao.getPetition(id);
        if (petition == null) {
            throw new PetitionNotFoundException("No petition!");
        }

        petitionDTO = petitionDtoExchanger.getPetitionDTO(petition);
        return petitionDTO;
    }
}
