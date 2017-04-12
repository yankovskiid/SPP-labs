package com.bsuir.petition.service.petition.util.impl;

import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.service.petition.util.PetitionDtoExchanger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetitionDtoExchangerImpl implements PetitionDtoExchanger {

    @Override
    public PetitionListDTO getPetitionListDTO(List<Petition> petitions) {
        return null;
    }

    @Override
    public PetitionDTO getPetitionDTO(Petition petition) {
        return null;
    }
}
