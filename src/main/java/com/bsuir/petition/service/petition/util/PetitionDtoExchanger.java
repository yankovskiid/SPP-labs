package com.bsuir.petition.service.petition.util;

import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;

import java.util.List;

public interface PetitionDtoExchanger {
    PetitionListDTO getPetitionListDTO(List<Petition> petitions);
    PetitionDTO getPetitionDTO(Petition petition);
}
