package com.bsuir.petition.service.petition.util;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;

public interface PetitionExchanger {
    Petition getPetition(AddPetitionDTO addPetitionDTO, User user);
}
