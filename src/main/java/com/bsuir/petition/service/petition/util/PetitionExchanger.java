package com.bsuir.petition.service.petition.util;

import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.entity.Petition;

public interface PetitionExchanger {
    Petition getPetition(PetitionDTO petitionDTO);
}
