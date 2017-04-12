package com.bsuir.petition.service.petition.util.impl;

import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.service.petition.util.PetitionExchanger;
import org.springframework.stereotype.Component;

@Component
public class PetitionExchangerImpl implements PetitionExchanger {
    @Override
    public Petition getPetition(PetitionDTO petitionDTO) {
        return null;
    }
}
